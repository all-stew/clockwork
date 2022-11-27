package com.zhaojj.clockwork.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class TypeUtilTest {

    @Test
    void transformNullLocalDateTime() {
        long epochSecond = TypeUtil.toLong(null);
        Assertions.assertEquals(0, epochSecond);
    }

    @Test
    void transformLongAndLocalDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        long epochSecond = TypeUtil.toLong(now);
        LocalDateTime time = TypeUtil.toLocalDateTime(epochSecond);
        Assertions.assertEquals(dateTimeFormatter.format(now), dateTimeFormatter.format(time));
    }
}