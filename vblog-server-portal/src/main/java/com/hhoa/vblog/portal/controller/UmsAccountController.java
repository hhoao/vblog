package com.hhoa.vblog.portal.controller;


import com.hhoa.vblog.common.api.CommonResult;
import com.hhoa.vblog.portal.bean.ResponseTokenInfo;
import com.hhoa.vblog.portal.bean.UmsAccountWrapper;
import com.hhoa.vblog.portal.bean.UmsLoginParam;
import com.hhoa.vblog.portal.service.UmsAccountService;
import com.hhoa.vblog.security.config.JwtSecurityProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 账户管理.
 *
 * @author hhoa
 * @since 2022/5/8
 **/
@Tags({@Tag(name = "账户管理", description = "UmsAccountController")})
@RestController
public class UmsAccountController {
    private final UmsAccountService accountService;

    private JwtSecurityProperties securityProperties;

    public UmsAccountController(UmsAccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setSecurityProperties(@Lazy JwtSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Operation(summary = "已认证获取账户信息")
    @GetMapping("/account")
    public CommonResult<UmsAccountWrapper> getAccount(@RequestHeader Map<String, String> headers) {
        String authorization = securityProperties.getTokenHeader().toLowerCase();
        UmsAccountWrapper accountByAuthorization = accountService.getAccountByAuthorization(
                headers.get(authorization));
        return CommonResult.success(accountByAuthorization);
    }

    @Operation(summary = "账户登录")
    @PostMapping("/accounts/auth/token")
    public CommonResult<ResponseTokenInfo> login(@RequestBody UmsLoginParam loginParam) {
        String token = accountService.login(loginParam);
        ResponseTokenInfo tokenInfo =
                new ResponseTokenInfo(token, securityProperties.getTokenHead());
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
        ResponseTokenInfo tokenInfo =
                new ResponseTokenInfo(refreshToken, securityProperties.getTokenHead());
        return CommonResult.success(tokenInfo);
    }

}
