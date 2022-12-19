package com.hhoa.vblog.common.api;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 通用返回对象.
 *
 * @author hhoa
 */
@Schema(description = "通用返回对象")
public class CommonResult<T> {
    /**
     * 状态码.
     */
    @Schema(description = "状态码")
    private int code;
    /**
     * 提示信息.
     */
    @Schema(description = "提示信息")
    private String message;
    /**
     * 数据封装.
     * //
     */
    @Schema(description = "数据")
    private T result;

    protected CommonResult() {
    }

    protected CommonResult(int code, String message, T result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    /**
     * 成功返回结果.
     *
     * @param result 要封装的数据
     * @param <T>  数据类型的泛型
     * @return 封装结果
     */
    public static <T> CommonResult<T> success(T result) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(), result);
    }

    public static <T> CommonResult<T> success(T result, String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, result);
    }

    /**
     * 失败返回结果.
     *
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果.
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message) {
        return new CommonResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果.
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果.
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果.
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果.
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果.
     */
    public static <T> CommonResult<T> unauthorized(T result) {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(),
                ResultCode.UNAUTHORIZED.getMessage(), result);
    }

    /**
     * 未授权返回结果.
     */
    public static <T> CommonResult<T> forbidden(T result) {
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(),
                ResultCode.FORBIDDEN.getMessage(), result);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
