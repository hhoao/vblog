package com.hhoa.vblog.common.api;

/**
 * IErrorCode.
 *
 * @author hhoa
 * @since 2022/4/7
 **/
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
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
