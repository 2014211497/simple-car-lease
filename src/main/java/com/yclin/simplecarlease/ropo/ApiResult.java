package com.yclin.simplecarlease.ropo;

import lombok.Data;

/**
 * This is a wrapper class for a call result
 *
 * @author LinYuchang
 */
@Data
public class ApiResult<T> {

    /**
     * Status code
     */
    protected int code;

    /**
     * The data
     */
    protected T data;

    /**
     * Some error or extra message
     */
    protected String message;

    /**
     * Duration of this api call, in milliseconds
     */
    protected Long took;

    public ApiResult() {
    }

    public ApiResult(int code, T data, String message, Long took) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.took = took;
    }

    public static <T> ApiResult<T> C200(T data) {
        return new ApiResult<>(200, data, null, null);
    }

    public static <T> ApiResult<T> C200(T data, String message) {
        return new ApiResult<>(200, data, message, null);
    }

    public static <T> ApiResult<T> C200401(T data, String message) {
        return new ApiResult<>(200401, data, message, null);
    }

    public static <T> ApiResult<T> C401(T data, String message) {
        return new ApiResult<>(401, data, message, null);
    }

    public static <T> ApiResult<T> C428(T data, String message) {
        return new ApiResult<>(428, data, message, null);
    }

    public static <T> ApiResult<T> C500(T data, String message) {
        return new ApiResult<>(500, data, message, null);
    }

    public static <T> ApiResult<T> C540(T data, String message) {
        return new ApiResult<>(518, data, message, null);
    }
}
