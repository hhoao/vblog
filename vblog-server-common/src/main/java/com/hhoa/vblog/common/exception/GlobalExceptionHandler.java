package com.hhoa.vblog.common.exception;


import com.hhoa.vblog.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理.
 *
 * @author hhoa
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handle common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public CommonResult<String> handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return CommonResult.failed(e.getErrorCode());
        }
        return CommonResult.failed(e.getMessage());
    }

    /**
     * Handle common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public CommonResult<String> handle(SQLIntegrityConstraintViolationException e) {
        return CommonResult.failed("有相关联的项目没有处理");
    }

    /**
     * Handle common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public CommonResult<String> handle(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return CommonResult.failed("系统内部出错: ");
    }

    /**
     * Handle common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = HttpMessageConversionException.class)
    public CommonResult<String> handle(HttpMessageConversionException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return CommonResult.failed("请求格式不正确");
    }


    /**
     * Handle valid exception common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResult<String> handleValidException(MethodArgumentNotValidException e) {
        return getFailedCommonResult(e);
    }

    /**
     * Handle valid exception common result.
     *
     * @param e the e
     * @return the common result
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public CommonResult<String> handleValidException(BindException e) {
        return getFailedCommonResult(e);
    }

    private CommonResult<String> getFailedCommonResult(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return CommonResult.validateFailed(message);
    }
}
