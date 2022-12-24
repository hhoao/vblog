package com.hhoa.vblog.portal.service.impl;

import com.hhoa.vblog.common.exception.ApiException;
import com.hhoa.vblog.mgb.model.UmsAccount;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.portal.TransactionTest;
import com.hhoa.vblog.portal.bean.PageInfo;
import com.hhoa.vblog.portal.bean.UmsAccountDetails;
import com.hhoa.vblog.portal.bean.UmsLoginParam;
import com.hhoa.vblog.portal.service.UmsAccountService;
import com.hhoa.vblog.security.util.JwtTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * UmsAccountServiceImplTest.
 *
 * @author hhoa
 * @since 2022/5/31
 **/

class UmsAccountServiceImplTest extends TransactionTest {
    @Autowired
    UmsAccountService accountService;
    @Autowired
    JwtTokenService jwtTokenService;
    String testPassword = "123456";
    String testAccountName = "test";
    String testErrorIdentifier = "error";

    @Test
    void logout() {
        UmsLoginParam umsLoginParam = new UmsLoginParam();
        umsLoginParam.setPassword(testPassword);
        umsLoginParam.setUsername(testAccountName);
        String login = accountService.login(umsLoginParam);
        accountService.logout(jwtTokenService.getTokenHead() + login);
    }

    @Test
    void getAccountDetails() {
        UserDetails test = accountService.getAccountDetails(testAccountName);
        Assertions.assertInstanceOf(UmsAccountDetails.class, test);
        Assertions.assertThrows(ApiException.class,
                () -> accountService.getAccountDetails(testErrorIdentifier));
    }

    public String getAuthorization() {
        UmsLoginParam umsLoginParam = new UmsLoginParam();
        umsLoginParam.setPassword("123456");
        umsLoginParam.setUsername("test");
        String token = accountService.login(umsLoginParam);
        return jwtTokenService.getTokenHead() + token;
    }

    @Test
    void refreshToken() {
        String authorization = getAuthorization();
        accountService.refreshToken(authorization);
    }

    @Test
    void list() {
        List<UmsAccount> list = accountService.list(new PageInfo(1, 5), new UmsAccount());
        Assertions.assertTrue(list.size() <= 5);
        list = accountService.list(new PageInfo(1, 5), null);
        Assertions.assertTrue(list.size() <= 5);
    }

    @Test
    void getAccountByName() {
        UmsAccount accountByName = accountService.getAccountByAccountName(testAccountName);
        Assertions.assertEquals(accountByName.getId(), 1L);
    }

    @Test
    void getAccountResources() {
        List<UmsResource> accountResources = accountService.getAccountResources(1L);
        Assertions.assertTrue(accountResources.size() > 0);
    }
}
