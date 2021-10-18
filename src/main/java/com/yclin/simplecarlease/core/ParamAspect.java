package com.yclin.simplecarlease.core;

import com.yclin.simplecarlease.param.CheckableParam;
import com.yclin.simplecarlease.param.CleanableParam;
import com.yclin.simplecarlease.ropo.ApiResult;
import com.yclin.simplecarlease.ropo.CheckResult;
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
public class ParamAspect implements Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Around("execution(public * com.yclin.simplecarlease.controller.*.*(..))")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof CheckableParam) {
                    CheckResult checkResult = ((CheckableParam) arg).check();
                    if (!checkResult.isOk()) {
                        return ApiResult.C428(null, checkResult.getMessage());
                    }
                }
                if (arg instanceof CleanableParam) {
                    ((CleanableParam) arg).clean();
                }
            }
        }
        return point.proceed();
    }

}
