package com.zhaojj11.clockwork.user.service.impl;

import com.zhaojj11.clockwork.common.constants.RedisConstants;
import com.zhaojj11.clockwork.common.exception.BaseException;
import com.zhaojj11.clockwork.common.utils.JwtUtil;
import com.zhaojj11.clockwork.exception.UserException;
import com.zhaojj11.clockwork.user.domain.model.Menu;
import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.domain.repository.MenuRepository;
import com.zhaojj11.clockwork.user.domain.repository.UserRepository;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDetails;
import com.zhaojj11.clockwork.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * UserServiceImpl
 *
 * @author zhaojj11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final RedisTemplate<String, String> redisTemplate;

    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    @NonNull
    @Override
    public String login(@NonNull LoginUserDTO dto) {
        // AuthenticationManager authenticate 进行用户认证
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);

            // 如果认证通过，使用username生成一个jwt
            LoginUserDetails loginUser = (LoginUserDetails) authenticate.getPrincipal();
            String username = loginUser.getUser().getUsername();
            String jwt = JwtUtil.createJwt(username);

            String loginUserKey = String.format(RedisConstants.USER_LOGIN_TOKEN, username);
            redisTemplate.opsForValue().set(loginUserKey, "", 24, TimeUnit.HOURS);
            return jwt;
        } catch (AuthenticationException e) {
            // 如果认证没过,会直接抛出异常
            throw new UserException(400, "登录失败");
        } catch (Exception e) {
            throw new BaseException(e.getMessage());
        }
    }

    @Override
    public void logout() {
        // 获取SecurityContextHolder中的用户username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUser = (LoginUserDetails) authentication.getPrincipal();
        String username = loginUser.getUser().getUsername();

        // 删除redis中的值
        String loginUserKey = String.format(RedisConstants.USER_LOGIN_TOKEN, username);
        redisTemplate.delete(loginUserKey);
    }

    @NonNull
    @Override
    public LoginUserDetails getLoginUserDetailDtoOrFail(@NonNull String username) {
        // 查询用户信息
        User user = userRepository.getByUsername(username);
        // 如果没有查询到用户，则抛出异常
        if (Objects.isNull(user)) {
            throw new UserException("invalid username or password");
        }

        // 获取权限名
        List<Menu> menus = menuRepository.listByUserId(user.getId());
        List<String> permissions = menus.stream().map(Menu::getPermission).toList();

        // 数据封装成UserDetails返回
        return new LoginUserDetails(user, permissions);
    }
}
