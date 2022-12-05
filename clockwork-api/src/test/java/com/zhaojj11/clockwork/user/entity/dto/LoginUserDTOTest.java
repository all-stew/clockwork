package com.zhaojj11.clockwork.user.entity.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginUserDTOTest {
    @Test
    void test() {
        LoginUserDTO dto = LoginUserDTO.build("test", "test");
        Assertions.assertEquals("test", dto.getUsername());
        Assertions.assertEquals("test", dto.getPassword());

        dto.setUsername("hello");
        dto.setPassword("hello");
        Assertions.assertEquals("hello", dto.getUsername());
        Assertions.assertEquals("hello", dto.getPassword());
    }
}