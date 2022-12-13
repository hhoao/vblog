package com.hhoa.blog.admin.controller;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.service.OmsFileService;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.common.api.UploadResult;
import com.hhoa.blog.mgb.model.OmsFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
    public UploadResult<String> upload(MultipartFile file) {
        String url = filesService.upload(file);
        return UploadResult.success(url);
    }

    @Operation(summary = "下载文件")
    @GetMapping("/file/{filesUUID}")
    public HttpServletResponse download(@PathVariable String filesUUID, HttpServletResponse response) {
        filesService.download(filesUUID, response);
        return null;
    }

    @Operation(summary = "根据id删除文件信息")
    @DeleteMapping("/files/{fileId}")
    public CommonResult<String> delete(@PathVariable("fileId") Long fileId) {
        filesService.deleteFileById(fileId);
        return CommonResult.success(null);
    }

}
