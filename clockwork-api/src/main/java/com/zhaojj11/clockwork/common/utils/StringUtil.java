package com.zhaojj11.clockwork.common.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

/**
 * @author zhaojj11
 */
@UtilityClass
public class StringUtil {

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
