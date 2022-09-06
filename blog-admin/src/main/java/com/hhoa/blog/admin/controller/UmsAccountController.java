package com.hhoa.blog.admin.controller;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.ResponseTokenInfo;
import com.hhoa.blog.admin.bean.UmsLoginParam;
import com.hhoa.blog.admin.service.UmsAccountService;
import com.hhoa.blog.common.api.CommonPage;
import com.hhoa.blog.common.api.CommonResult;
import com.hhoa.blog.mgb.model.UmsAccount;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.security.config.JwtSecurityProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 账户管理
 *
 * @author hhoa
 * @date 2022/5/8
 **/
@AllArgsConstructor
@Tags({@Tag(name = "账户管理", description = "UmsAccountController")})
@RestController
public class UmsAccountController {
    private final UmsAccountService accountService;
    private final JwtSecurityProperties securityProperties;

    @Operation(summary = "已认证分页获取账户角色菜单")
    @GetMapping("/account/role/menus")
    public CommonResult<CommonPage<UmsMenu>> getAccountRoleMenus(@RequestHeader Map<String, String> headers) {
        List<UmsMenu> menus = accountService.getMenusByAuthorization(
                headers.get(securityProperties.getTokenHeader().toLowerCase()));
        return CommonResult.success(CommonPage.restPage(menus));
    }

    @Operation(summary = "已认证获取账户信息")
    @GetMapping("/account")
    public CommonResult<UmsAccount> getAccount(@RequestHeader Map<String, String> headers) {
        String authorization = securityProperties.getTokenHeader().toLowerCase();
        UmsAccount account = accountService.getAccountByAuthorization(
                headers.get(authorization));
        return CommonResult.success(account);
    }

    @Operation(summary = "添加账户")
    @PostMapping("/accounts")
    public CommonResult<String> addAccount(@RequestBody UmsAccount account) {
        accountService.addAccount(account);
        return CommonResult.success(null);
    }

    @Operation(summary = "账户登录")
    @PostMapping("/accounts/auth/token")
    public CommonResult<ResponseTokenInfo> login(@RequestBody UmsLoginParam loginParam) {
        String token = accountService.login(loginParam);
        ResponseTokenInfo tokenInfo = new ResponseTokenInfo(token, securityProperties.getTokenHead());
        return CommonResult.success(tokenInfo);
    }

    @Operation(description = "账户退出, 需要前端删除token", summary = "账户退出")
    @DeleteMapping("/account/auth/token")
    public CommonResult<String> logout(@RequestHeader Map<String, String> headers) {
        String bearer = headers.get(securityProperties.getTokenHeader().toLowerCase());
        accountService.logout(bearer);
        return CommonResult.success(null);
    }

    @Operation(summary = "刷新token")
    @PatchMapping(value = "/account/auth/token")
    @ResponseBody
    public CommonResult<ResponseTokenInfo> refreshToken(HttpServletRequest request) {
        String authorization = request.getHeader(securityProperties.getTokenHeader());
        String refreshToken = accountService.refreshToken(authorization);
        ResponseTokenInfo tokenInfo = new ResponseTokenInfo(refreshToken, securityProperties.getTokenHead());
        return CommonResult.success(tokenInfo);
    }

    @Operation(summary = "分页获取账户列表")
    @GetMapping("/accounts")
    public CommonResult<CommonPage<UmsAccount>> getAccountByAccountParam(UmsAccount account,
                                                                         PageInfo pageInfo) {
        List<UmsAccount> accounts = accountService.list(pageInfo, account);
        for (UmsAccount subAccount : accounts) {
            subAccount.setPassword(null);
        }
        return CommonResult.success(CommonPage.restPage(accounts));
    }

    @Operation(summary = "更新账户资料")
    @PatchMapping("/accounts")
    public CommonResult<String> updateAccount(@RequestBody UmsAccount newAccount) {
        accountService.updateAccount(newAccount);
        return CommonResult.success(null);
    }

    @Operation(summary = "删除账户")
    @DeleteMapping("/accounts/{accountId}")
    public CommonResult<String> deleteAccount(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccountByAccountId(accountId);
        return CommonResult.success(null);
    }
}
