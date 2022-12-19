package com.hhoa.vblog.portal.controller;

import com.hhoa.vblog.portal.service.OmsFileService;
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
    @GetMapping("/file/{filesUuid}")
    public HttpServletResponse download(@PathVariable String filesUuid,
                                        HttpServletResponse response) {
        filesService.download(filesUuid, response);
        return null;
    }
}
