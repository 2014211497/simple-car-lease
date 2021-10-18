package com.yclin.simplecarlease.core;

import lombok.Data;

/**
 * @author LinYuchang
 */
@Data
public class AccessContext {

    protected String requestId;

    protected Long arriveTime;

    protected String method;

    protected String api;

    protected Integer code;

    protected Integer status;

    protected Long took;

    protected String userId;
}
