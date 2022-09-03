package com.hhoa.blog.admin.controller;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsRoleParam;
import com.hhoa.blog.admin.service.UmsRoleResourceRelationService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.mgb.model.UmsRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理
 *
 * @author hhoa
 * @date 2022/5/16
 **/
@RestController
@Tag(description = "UmsRoleController", name = "角色管理")
@RequiredArgsConstructor
public class UmsRoleController {
    private final UmsRoleService roleService;
    private final UmsRoleResourceRelationService resourceRoleRelationService;

    @Operation(summary = "授予角色资源")
    @PostMapping("/roles/{roleName}/resource/{resourceName}")
    public CommonResult<String> insertRoleResource(@PathVariable("roleName") String roleName,
                                                   @PathVariable("resourceName") String resourceName) {
        resourceRoleRelationService.addRoleResource(roleName, resourceName);

        return CommonResult.success(null);
    }

    @Operation(summary = "分页获取角色所有资源")
    @GetMapping("/roles/{roleName}/resources")
    public CommonResult<CommonPage<UmsResource>> listRoleResources(@Parameter(description = "角色名字") @PathVariable("roleName") String roleName,
                                                                   PageInfo pageInfo) {
        List<UmsResource> resources = resourceRoleRelationService.listRoleResources(roleName, pageInfo);
        return CommonResult.success(CommonPage.restPage(resources));
    }

    @Operation(summary = "删除角色资源")
    @DeleteMapping("/roles/{roleName}/resources/{resourceName}")
    public CommonResult<String> deleteRoleResource(@PathVariable("roleName") String roleName,
                                                   @PathVariable("resourceName") String resourceName) {
        resourceRoleRelationService.deleteRoleResource(roleName, resourceName);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除角色菜单")
    @DeleteMapping("/roles/{roleName}/menus/{menuId}")
    public CommonResult<String> deleteRoleMenu(@PathVariable("roleName") String roleName,
                                               @PathVariable("menuId") Long menuId) {
        roleService.deleteRoleMenu(roleName, menuId);
        return CommonResult.success(null);
    }

    @Operation(summary = "添加角色菜单")
    @PostMapping("/roles/{roleName}/menus/{menuId}")
    public CommonResult<String> addRoleMenu(@PathVariable("roleName") String roleName,
                                            @PathVariable("menuId") Long menuId) {
        roleService.addRoleMenu(roleName, menuId);
        return CommonResult.success(null);
    }

    @Operation(summary = "增加角色")
    @PostMapping("/roles")
    public CommonResult<String> addRole(@RequestBody UmsRoleParam roleParam) {
        roleService.addRole(roleParam);
        return CommonResult.success(null);
    }

    @Operation(summary = "设置角色资源")
    @PostMapping("/roles/{roleName}/resources")
    public CommonResult<String> allocResources(@PathVariable("roleName") String roleName,
                                               @RequestBody List<Long> resourceIds) {
        roleService.allocResources(roleName, resourceIds);
        return CommonResult.success(null);
    }

    @Operation(summary = "设置角色菜单")
    @PostMapping("/roles/{roleName}/menus")
    public CommonResult<String> allocMenus(@PathVariable("roleName") String roleName,
                                           @RequestBody List<Long> menuIds) {
        roleService.allocMenus(roleName, menuIds);
        return CommonResult.success(null);
    }

    @Operation(summary = "分页获取角色菜单列表")
    @GetMapping("/roles/{roleName}/menus")
    public CommonResult<CommonPage<UmsMenu>> list(PageInfo pageInfo,
                                                  @PathVariable(value = "roleName") String roleName) {
        List<UmsMenu> list = roleService.listMenus(pageInfo, roleName);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @Operation(summary = "分页获取角色列表")
    @GetMapping("/roles")
    public CommonResult<CommonPage<UmsRole>> list(PageInfo pageInfo, UmsRole role) {
        List<UmsRole> list = roleService.list(pageInfo, role);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/roles/{roleName}")
    public CommonResult<String> delRole(@PathVariable("roleName") String roleName) {
        roleService.deleteRole(roleName);
        return CommonResult.success(null);
    }

    @Operation(summary = "修改角色")
    @PatchMapping("/roles/{roleName}")
    public CommonResult<String> updateRole(@PathVariable("roleName") String roleName,
                                           @RequestBody UmsRoleParam roleParam) {
        roleService.updateRole(roleName, roleParam);
        return CommonResult.success(null);
    }
}
