package com.zhaojj11.clockwork.user.entity.vo.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class LoginUserRequestVOTest {
    @Test
    void test() {
        LoginUserRequestVO vo = new LoginUserRequestVO();
        assertNull(vo.getUsername());
        assertNull(vo.getPassword());
    }
}