package com.zhaojj11.clockwork.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhaojj11
 */
@UtilityClass
public class JwtUtil {
    /**
     * jwt ttl 1小时
     */
    public static final long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 密钥铭文
     */
    public static final String JWT_KEY = "test";

    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成默认的jwt
     *
     * @param subject token中需要存放的数据
     * @return jwt
     */
    public static String createJwt(String subject) {
        return getJwtBuilder(subject, JWT_TTL, getUuid()).compact();
    }

    /**
     * 生成指定过期时间的jwt
     *
     * @param subject   token中需要存放的数据
     * @param ttlMillis token超时时间
     * @return jwt
     */
    public static String createJwt(String subject, long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, getUuid()).compact();
    }

    /**
     * 生成指定uuid且指定过期时间的jwt
     *
     * @param uuid      uuid
     * @param subject   token中需要存放的数据
     * @param ttlMillis token超时时间
     * @return jwt
     */
    public static String createJwt(String uuid, String subject, long ttlMillis) {
        return getJwtBuilder(subject, ttlMillis, uuid).compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                // 唯一ID
                .setId(uuid)
                // 主题 可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("clockwork")
                // 签发时间
                .setIssuedAt(now)
                // 加密
                .signWith(signatureAlgorithm, secretKey)
                // 过期时间
                .setExpiration(expDate);
    }

    /**
     * 生成加密后的密钥 secretKey
     *
     * @return 密钥
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析jwt信息
     *
     * @param jwt jwt字符串
     * @return Claims
     */
    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(generalKey())
                .parseClaimsJwt(jwt)
                .getBody();
    }
}
