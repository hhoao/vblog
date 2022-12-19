package com.hhoa.vblog.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhoa.vblog.mgb.model.OmsFile;
import com.hhoa.vblog.portal.dao.OmsFileDao;
import com.hhoa.vblog.portal.service.OmsFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class OmsFileServiceImpl implements OmsFileService {
    private final OmsFileDao fileDao;

    @Value("${file.upload.path}")
    private String filesUploadPath; //获取文件路径
    @Value("${file.upload.baseUrl}")
    private String baseUrl;

    //将文件以流的形式一次性读取到内存，通过响应输出流输出到前端
    @Override
    public void download(String filesUuid, HttpServletResponse response) {
        try {
            //根据文件的唯一标识码获取文件
            File uploadFile = new File(filesUploadPath + filesUuid);

            //读取文件的字节流
            FileInputStream fileInputStream = new FileInputStream(uploadFile);
            //将文件写入输入流
            InputStream inputStream = new BufferedInputStream(fileInputStream);

            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();


            //attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.png"
            //filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(filesUuid, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");

            //设置输出流的格式
            ServletOutputStream os = response.getOutputStream();
            os.write(buffer);

            //关闭
            fileInputStream.close();
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件下载出错", e);
        }
    }


    //通过文件uuid查询文件
    private OmsFile getFileByUuid(String uuid) {
        //查找数据库是否已经存在一样的图片
        QueryWrapper<OmsFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        List<OmsFile> retFileList = fileDao.selectList(queryWrapper);
        return retFileList.size() == 0 ? null : retFileList.get(0);
    }
}

