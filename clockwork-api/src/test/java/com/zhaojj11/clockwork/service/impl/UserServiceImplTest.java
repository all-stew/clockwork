package com.zhaojj11.clockwork.service.impl;

import com.zhaojj11.clockwork.domain.dao.UserDao;
import com.zhaojj11.clockwork.domain.model.User;
import com.zhaojj11.clockwork.service.user.UserService;
import com.zhaojj11.clockwork.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
class UserServiceImplTest {

    private static final UserService userService = new UserServiceImpl(null, null);
    @MockBean
    private static UserDao userDao;

    @BeforeAll
    static void init() throws Exception {
        userDao = (UserDao) initBean();
    }

    // TODO 逻辑抽离出去 并且Mock脱离数据库+不启动Spring+优化测试速度+不引入项目组件
    static Object initBean() throws Exception {
        Class<?> clazzRoot = ((Object) UserServiceImplTest.userService).getClass();
        Object memberFieldObj = Mockito.mock((Class<?>) UserDao.class);
        String simpleName = UserDao.class.getSimpleName();
        Field field = clazzRoot.getDeclaredField(simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1));
        field.setAccessible(true);
        field.set(UserServiceImplTest.userService, memberFieldObj);
        return memberFieldObj;
    }


    @Test
    void save() {
        User user = User.builder().username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
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
}