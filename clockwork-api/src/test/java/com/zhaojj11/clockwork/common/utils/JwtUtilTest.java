package com.zhaojj11.clockwork.common.utils;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUtilTest {

    @Test
    void generalKey() {
        SecretKey secretKey = JwtUtil.generalKey();
        SecretKey secretKey2 = JwtUtil.generalKey();
        assertEquals(secretKey.getFormat(), secretKey2.getFormat());
        assertArrayEquals(secretKey.getEncoded(), secretKey2.getEncoded());
        assertEquals(secretKey.getAlgorithm(), secretKey2.getAlgorithm());
    }
}