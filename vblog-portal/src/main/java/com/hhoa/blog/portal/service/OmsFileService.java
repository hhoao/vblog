package com.hhoa.blog.portal.service;


import javax.servlet.http.HttpServletResponse;


public interface OmsFileService {
    /**
     * 下载文件
     *
     * @param filesUUID the files uuid
     * @param response  the response
     */
    void download(String filesUUID, HttpServletResponse response);
}
