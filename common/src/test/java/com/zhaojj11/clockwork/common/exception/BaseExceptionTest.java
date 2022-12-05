package com.zhaojj11.clockwork.common.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseExceptionTest {
    @Test
    void testBaseException() {
        Assertions.assertThrows(BaseException.class, () -> {
            throw new BaseException("hello");
        }, "hello");

        Assertions.assertThrows(BaseException.class, () -> {
            throw new BaseException(new Exception("hello"));
        }, "hello");

        try {
            throw new BaseException(200, "hello");
        } catch (BaseException e) {
            Assertions.assertEquals("hello", e.getMessage());
            Assertions.assertEquals(200, e.getCode());
        }
    }
}