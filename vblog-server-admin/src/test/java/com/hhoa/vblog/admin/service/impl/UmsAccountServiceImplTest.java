package com.hhoa.vblog.admin.service.impl;

import java.util.List;
import com.hhoa.vblog.admin.TransactionTest;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsAccountDetails;
import com.hhoa.vblog.admin.bean.UmsLoginParam;
import com.hhoa.vblog.admin.service.UmsAccountService;
import com.hhoa.vblog.mgb.model.UmsAccount;
import com.hhoa.vblog.mgb.model.UmsResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import com.hhoa.vblog.security.util.JwtTokenService;
import com.hhoa.vblog.common.exception.ApiException;

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
    void updateAccount() {
        UmsAccount account = new UmsAccount();
        account.setId(1L);
        String name = "xiaoli";
        account.setUsername(name);
        accountService.updateAccount(account);
        Assertions.assertDoesNotThrow(() -> accountService.getAccountByAccountName(name));
    }

    @Test
    void deleteAccountByAccountId() {
        accountService.deleteAccountByAccountId(1L);
        Assertions.assertThrows(Exception.class,
                () -> accountService.getAccountByAccountName(testAccountName));
    }

    @Test
    void getAccount() {
        accountService.getAccounts(1L);
        UmsAccount account = new UmsAccount();
        account.setIntroduce("Hello");
        List<UmsAccount> account1 = accountService.getAccounts(account);
        for (UmsAccount account2 : account1) {
            Assertions.assertEquals(account2.getIntroduce(), "HelloWorld");
        }
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

    @Test
    void deleteAccounts() {
        UmsAccount account = new UmsAccount();
        account.setRoleId(3L);
        accountService.deleteAccounts(account);
        List<UmsAccount> account1 = accountService.getAccounts(account);
        Assertions.assertEquals(account1.size(), 0);
    }
}
