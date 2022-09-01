package com.hhoa.blog.admin.service;


import com.hhoa.blog.mgb.model.OmsFile;
import com.hhoa.blog.admin.bean.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;



public interface OmsFileService {

    /**
     * 分页获取文件信息
     *
     * @param pageInfo  the page info
     * @param queryFile
     * @return list file list
     */
    List<OmsFile> getFileList(PageInfo pageInfo, OmsFile queryFile);

    /**
     * 上传文件
     *
     * @param file the file
     * @return file string
     */
    String upload(MultipartFile file);

    /**
     * 下载文件
     *
     * @param filesUUID the files uuid
     * @param response  the response
     */
    void download(String filesUUID, HttpServletResponse response);

    /**
     * 删除文件信息
     *
     * @param id the id
     */
    void deleteFileById(Long id);
}
