package com.zhaojj11.clockwork.user.filter;

import com.zhaojj11.clockwork.common.constants.RedisConstants;
import com.zhaojj11.clockwork.common.utils.JwtUtil;
import com.zhaojj11.clockwork.exception.UserException;
import com.zhaojj11.clockwork.user.domain.dao.UserDao;
import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author zhaojj11
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, String> redisTemplate;
    private final UserDao userDao;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        // 如果没有token，则没有登陆过，就放行去登录
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String username;
        try {
            Claims claims = JwtUtil.parseJwt(token);
            username = claims.getSubject();
        } catch (Exception e) {
            throw new UserException(401, "unauthorized");
        }

        // 从redis中获取用户
        String loginUserKey = String.format(RedisConstants.USER_LOGIN_TOKEN, username);
        Long expire = redisTemplate.getExpire(loginUserKey);
        if (expire == null || expire <= 0) {
            redisTemplate.delete(loginUserKey);
            throw new UserException(401, "token is expire");
        }
        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UserException("invalid username or password");
        }
        UserDetailsServiceImpl.LoginUser loginUser = new UserDetailsServiceImpl.LoginUser(user);

        // 存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
