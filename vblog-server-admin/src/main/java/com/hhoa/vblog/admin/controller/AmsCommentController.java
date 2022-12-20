package com.hhoa.vblog.admin.controller;

import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.service.AmsCommentService;
import com.hhoa.vblog.common.api.CommonPage;
import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.mgb.model.AmsComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Ams comment controller.
 *
 * @author hhoa
 * @since 2022 /9/3
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "评论管理", description = "AmsCommentController")
public class AmsCommentController {
    private final AmsCommentService commentService;

    @Operation(description = "分页获取评论列表", summary = "分页获取评论列表")
    @GetMapping("/comments")
    @Parameters({@Parameter()})
    public CommonResult<CommonPage<AmsComment>> list(PageInfo pageInfo,
                                                     AmsComment commentParams) {
        List<AmsComment> amsComments = commentService.list(commentParams, pageInfo);
        return CommonResult.success(CommonPage.restPage(amsComments));
    }

    @Operation(description = "添加评论", summary = "添加评论")
    @PostMapping("/comments")
    public CommonResult<String> addComment(@RequestBody AmsComment commentParam) {
        commentService.addComment(commentParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "修改评论")
    @PatchMapping("/comments")
    public CommonResult<String> updateComment(@RequestBody AmsComment commentParam) {
        commentService.updateComment(commentParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除评论")
    @DeleteMapping("/comments/{commentId}")
    public CommonResult<String> delComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return CommonResult.success(null);
    }
}
