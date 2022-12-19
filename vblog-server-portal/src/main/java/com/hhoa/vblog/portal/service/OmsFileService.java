package com.hhoa.vblog.portal.service;


import javax.servlet.http.HttpServletResponse;


/**
 * The interface Oms file service.
 */
public interface OmsFileService {
    /**
     * 下载文件.
     *
     * @param filesUuid the files uuid
     * @param response  the response
     */
    void download(String filesUuid, HttpServletResponse response);
}
