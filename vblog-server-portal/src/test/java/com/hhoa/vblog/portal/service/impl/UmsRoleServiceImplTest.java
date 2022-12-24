package com.hhoa.vblog.portal.service.impl;

import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.portal.TransactionTest;
import com.hhoa.vblog.portal.service.UmsRoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UmsRoleServiceImplTest.
 *
 * @author hhoa
 * @since 2022/6/3
 **/

class UmsRoleServiceImplTest extends TransactionTest {
    private static final String testRoleName = "ROLE_ADMIN";
    @Autowired
    UmsRoleService roleService;

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
}
