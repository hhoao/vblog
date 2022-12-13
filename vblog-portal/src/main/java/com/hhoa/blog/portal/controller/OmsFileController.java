package com.hhoa.blog.portal.controller;

import com.hhoa.blog.portal.service.OmsFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequiredArgsConstructor
@Tag(name = "文件管理", description = "OmsFileController")
public class OmsFileController {

    private final OmsFileService filesService;
    @Operation(summary = "下载文件")
    @GetMapping("/file/{filesUUID}")
    public HttpServletResponse download(@PathVariable String filesUUID, HttpServletResponse response) {
        filesService.download(filesUUID, response);
        return null;
    }
}
