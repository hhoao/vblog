package com.hhoa.blog.portal.controller;

import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.AmsComment;
import com.hhoa.blog.portal.bean.PageInfo;
import com.hhoa.blog.portal.service.AmsCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hhoa
 * @since 2022/9/3
 **/

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
}
