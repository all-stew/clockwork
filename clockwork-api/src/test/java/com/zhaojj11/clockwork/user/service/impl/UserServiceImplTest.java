package com.zhaojj11.clockwork.user.service.impl;

import com.zhaojj11.clockwork.common.constants.RedisConstants;
import com.zhaojj11.clockwork.common.exception.BaseException;
import com.zhaojj11.clockwork.exception.UserException;
import com.zhaojj11.clockwork.user.domain.dao.UserDao;
import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Import({UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    private static final Fairy fairy = Fairy.create();
    @Mock
    ValueOperations<String, String> valueOperations;
    @Resource
    private UserServiceImpl userService;
    @MockBean
    private UserDao userDao;
    @MockBean
    private RedisTemplate<String, String> redisTemplate;
    @MockBean
    private AuthenticationConfiguration authenticationConfiguration;
    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void save() {
        User user = User.builder().username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).nickname("test").avatar("avatar").createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userDao.save(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean saved = userService.save(user);
        assertTrue(saved);
    }

    @Test
    void remove() {
        Mockito.when(userService.remove(Mockito.anyLong())).thenReturn(true);
        boolean removed = userService.remove(6L);
        assertTrue(removed);
    }

    @Test
    void updateById() {
        User user = User.builder().id(1L).username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userService.updateById(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean updated = userService.updateById(user);
        assertNotNull(user);
        assertTrue(updated);
    }

    @Test
    void getById() {
        User userData = User.builder().id(1L).username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userService.getById(Mockito.anyLong())).thenReturn(userData);
        User user = userService.getById(6);
        assertNotNull(user);
    }

    @Test
    void testLoginSuccessful() throws Exception {
        Person person = fairy.person();
        String username = person.getUsername();
        String password = person.getPassword();
        LoginUserDTO loginUserDTO = LoginUserDTO.build(username, password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(password);
        User userData = User.builder().id(1L)
                .username(username)
                .password(encode)
                .status(User.UserStatus.ENABLE)
                .build();

        Mockito.when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());

        UserDetailsServiceImpl.LoginUser loginUser = new UserDetailsServiceImpl.LoginUser();
        loginUser.setUser(userData);
        UsernamePasswordAuthenticationToken authedToken = new UsernamePasswordAuthenticationToken(loginUser, loginUserDTO.getPassword());
        Mockito.when(authenticationManager.authenticate(token)).thenReturn(authedToken);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        Mockito.doAnswer(invocation -> {
            Object arg0 = invocation.getArgument(0);
            String key = String.format(RedisConstants.USER_LOGIN_TOKEN, username);
            assertEquals(arg0, key);
            return null;
        }).when(valueOperations).set(Mockito.anyString(), Mockito.anyString());

        String login = userService.login(loginUserDTO);
        assertTrue(StringUtils.isNotBlank(login));
    }

    @Test
    void testLoginFail() throws Exception {
        Person person = fairy.person();
        String username = person.getUsername();
        String password = person.getPassword();
        LoginUserDTO loginUserDTO = LoginUserDTO.build(username, password);

        Mockito.when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        Mockito.when(authenticationManager.authenticate(token)).thenThrow(InternalAuthenticationServiceException.class);

        Assertions.assertThrows(UserException.class, () -> userService.login(loginUserDTO), "登录失败");
    }

    @Test
    void testLoginFail2() throws Exception {
        Person person = fairy.person();
        String username = person.getUsername();
        String password = person.getPassword();
        LoginUserDTO loginUserDTO = LoginUserDTO.build(username, password);

        Mockito.when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());
        Mockito.when(authenticationManager.authenticate(token)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(BaseException.class, () -> userService.login(loginUserDTO));
    }
}