package com.hhoa.vblog.common.api;

/**
 * @author hhoa
 * @since 2022/4/7
 **/
public interface IErrorCode {
    /**
     * 获取码.
     *
     * @return 码
     */
    int getCode();

    /**
     * 返回信息.
     *
     * @return 信息
     */
    String getMessage();
}
