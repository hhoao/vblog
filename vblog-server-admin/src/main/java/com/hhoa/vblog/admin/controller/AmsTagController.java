package com.hhoa.vblog.admin.controller;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsTagService;
import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.AmsTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Ams tag controller.
 *
 * @author hhoa
 * @since 2022 /12/12
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "文章标签管理", description = "AmsTagController")
public class AmsTagController {
    private final AmsTagService tagService;

    @Operation(summary = "分页获取标签列表")
    @GetMapping("/tags")
    public CommonResult<CommonPage<AmsTag>> list(PageInfo pageInfo,
                                                 AmsTag tag) {
        List<AmsTag> tags = tagService.list(tag, pageInfo);
        return CommonResult.success(CommonPage.restPage(tags));
    }

    @Operation(summary = "添加标签")
    @PostMapping("/tags")
    public CommonResult<String> addTag(@RequestBody @Validated AmsTag tagParam) {
        tagService.addTag(tagParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "修改标签")
    @PutMapping("/tags")
    public CommonResult<String> updateTag(@RequestBody AmsTag tagParam) {
        tagService.updateTag(tagParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除标签")
    @DeleteMapping("/tags/{tagId}")
    public CommonResult<String> delTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return CommonResult.success(null);
    }
}
