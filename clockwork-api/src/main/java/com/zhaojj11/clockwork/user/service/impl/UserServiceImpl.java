package com.zhaojj11.clockwork.user.service.impl;

import com.zhaojj.clockwork.common.constants.RedisConstants;
import com.zhaojj11.clockwork.common.utils.JwtUtil;
import com.zhaojj11.clockwork.exception.UserException;
import com.zhaojj11.clockwork.user.domain.dao.UserDao;
import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import com.zhaojj11.clockwork.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

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
    private final UserDao userDao;


    @Override
    public boolean save(@NotNull User user) {
        return userDao.save(user);
    }

    @Override
    public boolean remove(@NotNull Long id) {
        return userDao.removeById(id);
    }

    @Override
    public boolean updateById(@NotNull User user) {
        return userDao.updateById(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public String login(LoginUserDTO dto) {
        // AuthenticationManager authenticate 进行用户认证
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            Authentication authenticate = authenticationManager.authenticate(token);

            // 如果认证通过，使用username生成一个jwt
            UserDetailsServiceImpl.LoginUser loginUser = (UserDetailsServiceImpl.LoginUser) authenticate.getPrincipal();
            String username = loginUser.getUser().getUsername();
            String jwt = JwtUtil.createJwt(username);

            String loginUserKey = String.format(RedisConstants.USER_LOGIN_TOKEN, username);
            redisTemplate.opsForValue().set(loginUserKey, jwt);
            return jwt;
        } catch (AuthenticationException e) {
            // 如果认证没过,会直接抛出异常
            throw new UserException(400, "登录失败");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
