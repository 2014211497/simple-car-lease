package com.yclin.simplecarlease.core;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LinYuchang
 */
@Slf4j
@Component
public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccessContext context = new AccessContext();
        context.setRequestId(IdGenerator.uuid32());
        context.setApi(request.getServletPath());
        context.setMethod(request.getMethod());
        context.setArriveTime(System.currentTimeMillis());

        ContextHolder.setAccessContext(context);

        // set value into logging tools
        MDC.put("qid", context.getRequestId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AccessContext context = ContextHolder.getAccessContext();
        context.setTook(System.currentTimeMillis() - context.getArriveTime());
        context.setStatus(response.getStatus());
        log.info("qid={}, art={}, api={}, mth={}, status={}, code={}, took={}",
                context.getRequestId(), context.getArriveTime(), context.getApi(),
                context.getMethod(), context.getStatus(), context.getCode(), context.getTook());
    }
}
