package com.zhaojj11.clockwork.user.entity.vo.response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LoginUserResponseVOTest {

    @Test
    void test() {
        LoginUserResponseVO vo = LoginUserResponseVO.build("test");
        Assertions.assertEquals("test", vo.getToken());

        vo.setToken("hello");
        Assertions.assertEquals("hello", vo.getToken());
    }
}