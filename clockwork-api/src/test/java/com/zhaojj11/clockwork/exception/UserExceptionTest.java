package com.zhaojj11.clockwork.exception;

import com.zhaojj11.clockwork.common.exception.BaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserExceptionTest {
    @Test
    void testUserException() {

        Assertions.assertThrows(UserException.class, () -> {
            throw new UserException("hello");
        }, "hello");

        Assertions.assertThrows(UserException.class, () -> {
            throw new UserException(new Exception("hello"));
        }, "hello");

        try {
            throw new UserException(200, "hello");
        } catch (BaseException e) {
            Assertions.assertEquals("hello", e.getMessage());
            Assertions.assertEquals(200, e.getCode());
        }
    }
}