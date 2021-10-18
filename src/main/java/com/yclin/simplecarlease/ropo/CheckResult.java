package com.yclin.simplecarlease.ropo;

import lombok.Data;

/**
 * @author LinYuchang
 */
@Data
public class CheckResult {

    protected boolean ok;

    protected String message;

    protected Throwable exception;

    public CheckResult(boolean ok, String message, Throwable exception) {
        this.ok = ok;
        this.message = message;
        this.exception = exception;
    }

    public static CheckResult success() {
        return new CheckResult(true, null, null);
    }

    public static CheckResult fail(String message) {
        return new CheckResult(false, message, null);
    }

    public static CheckResult error(String message, Throwable e) {
        return new CheckResult(false, message, e);
    }
}
