package com.hhoa.vblog.admin.service.impl;

import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsRoleParam;
import com.hhoa.vblog.admin.service.UmsRoleService;
import com.hhoa.vblog.common.exception.ApiException;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UmsRoleServiceImplTest.
 *
 * @author hhoa
 * @since 2022/6/3
 **/

class UmsRoleServiceImplTest extends TransactionTest {
    private final String testRoleName = "ROLE_ADMIN";
    @Autowired
    UmsRoleService roleService;

    static List<Object[]> listParamsProvider() {
        List<Object[]> params = new ArrayList<>();
        UmsRole role = null;

        role = new UmsRole();
        role.setName("ROLE_ADMIN");
        params.add(new Object[]{new PageInfo(1, 5), role,
            (Consumer<List<Object>>) o -> Assertions.assertTrue(o.size() > 0)});

        role = new UmsRole();
        role.setId(1L);
        params.add(new Object[]{new PageInfo(1, 5), role,
            (Consumer<List<Object>>) o -> Assertions.assertTrue(o.size() > 0)});

        params.add(new Object[]{new PageInfo(1, 1), null,
            (Consumer<List<Object>>) o -> assertEquals(1, o.size())});

        return params;
    }

    static List<Object[]> allocResourcesParams() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{"ROLE_ADMIN", List.of(1L, 2L)});
        return params;
    }

    @Test
    void updateRole() {
        UmsRoleParam roleParam = new UmsRoleParam();
        roleParam.setDescription("管理员");
        roleService.updateRole(testRoleName, roleParam);
    }

    @Test
    void getRoleResources() {
        List<UmsResource> roleResources = roleService.getRoleResources(1L);
        Assertions.assertNotNull(roleResources);
    }

    @Test
    void getRole() {
        UmsRole role = roleService.getRole(testRoleName);
        Assertions.assertEquals(role.getId(), 1L);
    }

    @Test
    void addRole() {
        UmsRoleParam roleParam = new UmsRoleParam();
        roleParam.setName("ROLE_CONTROLLER");
        roleService.addRole(roleParam);
        UmsRole roleController = roleService.getRole("ROLE_CONTROLLER");
        Assertions.assertEquals(roleController.getName(), "ROLE_CONTROLLER");
    }

    @Test
    void deleteRole() {
        roleService.deleteRole("ROLE_ADMIN");
        Assertions.assertThrows(ApiException.class, () -> roleService.getRole("ROLE_ADMIN"));
    }

    @ParameterizedTest
    @MethodSource("listParamsProvider")
    void list(PageInfo pageInfo, UmsRole role, Consumer<Object> consumer) {
        List<UmsRole> list = roleService.list(pageInfo, role);
        consumer.accept(list);
    }

    @ParameterizedTest
    @MethodSource("allocResourcesParams")
    void allocResources(String roleName, List<Long> resourcesIds) {
        roleService.allocResources(roleName, resourcesIds);
        UmsRole role = roleService.getRole(roleName);
        List<UmsResource> resources = roleService.getRoleResources(role.getId());
        Assertions.assertEquals(resourcesIds.size(), resources.size());
    }
}
