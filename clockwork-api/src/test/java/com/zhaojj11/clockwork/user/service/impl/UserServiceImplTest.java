package com.zhaojj11.clockwork.user.service.impl;

import com.zhaojj11.clockwork.common.constants.RedisConstants;
import com.zhaojj11.clockwork.common.exception.BaseException;
import com.zhaojj11.clockwork.exception.UserException;
import com.zhaojj11.clockwork.user.domain.model.User;
import com.zhaojj11.clockwork.user.domain.repository.MenuRepository;
import com.zhaojj11.clockwork.user.domain.repository.UserRepository;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import com.zhaojj11.clockwork.user.entity.dto.LoginUserDetails;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Import({UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    private static final Fairy fairy = Fairy.create();
    @Mock
    ValueOperations<String, String> valueOperations;
    @Resource
    private UserServiceImpl userService;
    @MockBean
    private RedisTemplate<String, String> redisTemplate;
    @MockBean
    private AuthenticationConfiguration authenticationConfiguration;
    @MockBean
    private AuthenticationManager authenticationManager;
    public static User userData;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MenuRepository menuRepository;

    @BeforeAll
    static void init() {
        Person person = fairy.person();

        String username = person.getUsername();
        String nickname = person.getUsername();
        String password = person.getPassword();
        String email = person.getEmail();
        String avatar = fairy.textProducer().randomString(10);

        userData = User.buildRegisterUser(username, nickname, password, email, avatar);
        userData.setId(1L);
    }

    @Test
    void testLoginSuccessful() throws Exception {
        Person person = fairy.person();
        String username = person.getUsername();
        String password = person.getPassword();
        LoginUserDTO loginUserDTO = LoginUserDTO.build(username, password);

        Mockito.when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword());

        LoginUserDetails loginUser = new LoginUserDetails(userData, new ArrayList<>());
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

    @Test
    void testGetLoginUserDetailDtoOrFail() {
        Mockito.when(userRepository.getByUsername(Mockito.anyString())).thenReturn(userData);
        Mockito.when(menuRepository.listByUserId(Mockito.anyLong())).thenReturn(new ArrayList<>());

        LoginUserDetails loginUserDetails = userService.getLoginUserDetailDtoOrFail("");
        Assertions.assertEquals(userData.getUsername(), loginUserDetails.getUsername());
        Assertions.assertEquals(0, loginUserDetails.getPermissions().size());
    }
}