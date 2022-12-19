package com.hhoa.vblog.admin.controller;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.OmsFileService;
import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.OmsFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "文件管理", description = "OmsFileController")
public class OmsFileController {

    private final OmsFileService filesService;

    @Operation(summary = "分页获取文件信息")
    @GetMapping("/files")
    public CommonResult<CommonPage<OmsFile>> list(PageInfo pageInfo, OmsFile file) {
        List<OmsFile> filesList = filesService.getFileList(pageInfo, file);
        return CommonResult.success(CommonPage.restPage(filesList));
    }

    @Operation(summary = "上传文件")
    @PostMapping("/files")
    @RequestBody(content = {
        @Content(
                mediaType = "multipart/form-data",
                schema = @Schema(type = "object"),
                schemaProperties = {
                    @SchemaProperty(
                                name = "file",
                                schema = @Schema(
                                        type = "string",
                                        format = "binary"
                                )
                        )
                })
    })
    public CommonResult<String> upload(MultipartFile file) {
        String url = filesService.upload(file);
        return CommonResult.success(url);
    }

    @Operation(summary = "下载文件")
    @GetMapping("/file/{filesUuid}")
    public HttpServletResponse download(
            @PathVariable String filesUuid,
            HttpServletResponse response) {
        filesService.download(filesUuid, response);
        return null;
    }

    @Operation(summary = "根据id删除文件信息")
    @DeleteMapping("/files/{fileId}")
    public CommonResult<String> delete(@PathVariable("fileId") Long fileId) {
        filesService.deleteFileById(fileId);
        return CommonResult.success(null);
    }

}
