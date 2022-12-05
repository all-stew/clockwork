package com.zhaojj11.clockwork.user.entity.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginUserDTOTest {
    @Test
    void test() {
        LoginUserDTO dto = LoginUserDTO.build("test", "test");
        Assertions.assertEquals(dto.getUsername(), "test");
        Assertions.assertEquals(dto.getPassword(), "test");

        dto.setUsername("hello");
        dto.setPassword("hello");
        Assertions.assertEquals(dto.getUsername(), "hello");
        Assertions.assertEquals(dto.getPassword(), "hello");
    }
}