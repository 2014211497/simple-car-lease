package com.yclin.simplecarlease.core;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.yclin.simplecarlease.ropo.ApiResult;
import com.yclin.simplecarlease.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author LinYuchang
 */
@Slf4j
@Aspect
@Component
public class TokenAspect implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Around("@within(com.yclin.simplecarlease.core.CarLeaseController) && " +
            "!@annotation(com.yclin.simplecarlease.core.IgnoreLogin)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.error("token verify failed: null or empty");
            return ApiResult.C401(null, "invalid access token");
        }
        try {
            DecodedJWT jwt = JwtUtil.decodeToken(token);
            ContextHolder.getAccessContext().setUserId(jwt.getClaim("uid").asString());
        } catch (Exception e) {
            log.error("token verify failed: {}", e.getMessage());
            return ApiResult.C401(null, "invalid access token");
        }
        return point.proceed();
    }

}
