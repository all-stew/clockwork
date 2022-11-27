package com.zhaojj11.clockwork.common.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtUtilTest {

    @Test
    void testCreateJwt() {
        String sub1 = "1234";
        String jwt1 = JwtUtil.createJwt(sub1);
        Claims claims1 = JwtUtil.parseJwt(jwt1);
        assertEquals(claims1.getSubject(), sub1);

        String sub2 = "12345";
        String jwt2 = JwtUtil.createJwt(sub2, 1000);
        Claims claims2 = JwtUtil.parseJwt(jwt2);
        assertEquals(claims2.getSubject(), sub2);

        String sub3 = "12345";
        String uuid = StringUtil.getUuid();
        String jwt3 = JwtUtil.createJwt(uuid, sub3, 2000);
        Claims claims3 = JwtUtil.parseJwt(jwt3);
        assertEquals(claims3.getSubject(), sub3);
    }

    @Test
    void generalKey() {
        SecretKey secretKey = JwtUtil.generalKey();
        SecretKey secretKey2 = JwtUtil.generalKey();
        assertEquals(secretKey.getFormat(), secretKey2.getFormat());
        assertArrayEquals(secretKey.getEncoded(), secretKey2.getEncoded());
        assertEquals(secretKey.getAlgorithm(), secretKey2.getAlgorithm());
    }
}