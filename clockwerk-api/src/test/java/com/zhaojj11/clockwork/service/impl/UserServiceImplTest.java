package com.zhaojj11.clockwork.service.impl;

import com.zhaojj11.clockwork.domain.dao.UserDao;
import com.zhaojj11.clockwork.domain.model.User;
import com.zhaojj11.clockwork.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
class UserServiceImplTest {

    @MockBean
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    void save() {
        User user = User.builder().username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userDao.save(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean saved = userService.save(user);
        assertTrue(saved);
    }

    @Test
    void remove() {
        Mockito.when(userDao.removeById(Mockito.anyLong())).thenReturn(true);
        boolean removed = userService.remove(6L);
        assertTrue(removed);
    }

    @Test
    void updateById() {
        User user = User.builder().id(1L).username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userDao.updateById(ArgumentMatchers.any(User.class))).thenReturn(true);
        boolean updated = userService.updateById(user);
        assertNotNull(user);
        assertTrue(updated);
    }

    @Test
    void getById() {
        User userData = User.builder().id(1L).username("test").email("test@test.com").status(User.UserStatus.ENABLE).password("").deleted(false).createdTime(LocalDateTime.now()).updatedTime(LocalDateTime.now()).build();
        Mockito.when(userDao.getById(Mockito.anyLong())).thenReturn(userData);
        User user = userService.getById(6);
        assertNotNull(user);
    }
}