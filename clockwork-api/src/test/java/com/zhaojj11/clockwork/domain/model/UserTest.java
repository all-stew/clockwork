package com.zhaojj11.clockwork.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setId(1L);
        Assertions.assertEquals(1L, user.getId());
        user.setUsername("test");
        Assertions.assertEquals("test", user.getUsername());
        user.setNickname("test");
        Assertions.assertEquals("test", user.getNickname());
        user.setPassword("test");
        Assertions.assertEquals("test", user.getPassword());
        user.setEmail("test");
        Assertions.assertEquals("test", user.getEmail());
        user.setStatus(User.UserStatus.ENABLE);
        Assertions.assertEquals(User.UserStatus.ENABLE, user.getStatus());
        user.setAvatar("test");
        Assertions.assertEquals("test", user.getAvatar());
        user.setDeleted(true);
        Assertions.assertTrue(user.getDeleted());
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedTime(now);
        Assertions.assertEquals(now, user.getCreatedTime());
        user.setUpdatedTime(now);
        Assertions.assertEquals(now, user.getUpdatedTime());
    }
}