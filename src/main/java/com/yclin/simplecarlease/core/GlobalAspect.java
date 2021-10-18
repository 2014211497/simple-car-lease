package com.yclin.simplecarlease.core;

import com.yclin.simplecarlease.ropo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author LinYuchang
 */
@Slf4j
@Aspect
@Component
public class GlobalAspect implements Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    @Around("@within(com.yclin.simplecarlease.core.CarLeaseController)")
    public Object doAround(ProceedingJoinPoint point) {
        ApiResult<?> result;
        try {
            Object proceed = point.proceed();
            result = ((ApiResult<?>) proceed);
        } catch (Throwable throwable) {
            log.error("uncaught exception:", throwable);
            result = ApiResult.C500(null, "something wrong with the server, please try again later please.");
        }
        result.setTook(System.currentTimeMillis() - ContextHolder.getAccessContext().getArriveTime());
        ContextHolder.getAccessContext().setCode(result.getCode());
        return result;
    }
}
