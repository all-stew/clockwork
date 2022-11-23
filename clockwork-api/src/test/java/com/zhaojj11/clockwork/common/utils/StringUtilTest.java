package com.zhaojj11.clockwork.common.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilTest {

    @Test
    void getUuid() {
        String uuid = StringUtil.getUuid();
        assertEquals(uuid.length(), 32);
    }
}