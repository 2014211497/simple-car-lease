package com.yclin.simplecarlease.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * @author LinYuchang
 */
public class JwtUtil {

    protected static final Algorithm ALGORITHM = Algorithm.HMAC256("random secret key");

    public static String createToken(String userId, Long expireAt) {
        return JWT.create().withClaim("uid", userId).withExpiresAt(new Date(expireAt)).sign(ALGORITHM);
    }

    public static DecodedJWT decodeToken(String token) {
        return JWT.require(ALGORITHM).build().verify(token);
    }
}
