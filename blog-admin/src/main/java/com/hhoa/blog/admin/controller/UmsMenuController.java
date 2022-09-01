package com.hhoa.blog.admin.controller;


import com.hhoa.blog.admin.service.UmsMenuService;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsMenuParam;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hhoa
 * @date 2022/6/13
 **/

@RestController
@RequiredArgsConstructor
@Tag(name = "菜单管理", description = "UmsMenuController")
public class UmsMenuController {
    private final UmsMenuService menuService;
    @Operation(description = "分页获取菜单列表", summary = "分页获取菜单列表")
    @GetMapping("/menus")
    public CommonResult<CommonPage<UmsMenu>> list(PageInfo pageInfo,
                                                  UmsMenu menuParams){
        List<UmsMenu> allMenus = menuService.list(pageInfo, menuParams);
        return CommonResult.success(CommonPage.restPage(allMenus));
    }

    @Operation(description = "添加菜单", summary = "添加菜单")
    @PostMapping("/menus")
    public CommonResult<String> addMenu(@RequestBody UmsMenuParam menuParam){
        menuService.addMenu(menuParam);
        return CommonResult.success(null);
    }
    @Operation(summary = "修改菜单")
    @PatchMapping("/menus/{menuName}")
    public CommonResult<String> updateMenu(@PathVariable("menuName") String menuName,
                                               @RequestBody UmsMenuParam menuParam){
        menuService.updateMenu(menuName, menuParam);
        return CommonResult.success(null);
    }
    @Operation(summary = "删除菜单")
    @DeleteMapping("/menus/{menuName}")
    public CommonResult<String> delMenu(@PathVariable String menuName){
        menuService.deleteMenu(menuName);
        return CommonResult.success(null);
    }
}
