package com.hhoa.vblog.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hhoa.vblog.admin.bean.PageInfo;
import com.hhoa.vblog.admin.bean.UmsAccountDetails;
import com.hhoa.vblog.admin.bean.UmsAccountWrapper;
import com.hhoa.vblog.admin.bean.UmsLoginParam;
import com.hhoa.vblog.admin.dao.UmsAccountDao;
import com.hhoa.vblog.admin.service.UmsAccountCacheService;
import com.hhoa.vblog.admin.service.UmsAccountService;
import com.hhoa.vblog.admin.service.UmsRoleService;
import com.hhoa.vblog.common.exception.Asserts;
import com.hhoa.vblog.mgb.model.UmsAccount;
import com.hhoa.vblog.mgb.model.UmsResource;
import com.hhoa.vblog.mgb.model.UmsRole;
import com.hhoa.vblog.security.util.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * The type Ums account service.
 *
 * @author hhoa
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UmsAccountServiceImpl implements UmsAccountService {
    private final PasswordEncoder passwordEncoder;
    private final UmsAccountCacheService accountCacheService;

    private final UmsAccountDao accountDao;

    private final JwtTokenService jwtTokenService;
    private final UmsRoleService roleService;

    @Override
    public String login(UmsLoginParam loginParam) {
        String token = null;
        try {
            UmsAccount account = getAccountByAccountName(loginParam.getUsername());
            if (!passwordEncoder.matches(loginParam.getPassword(), account.getPassword())) {
                Asserts.fail("密码错误");
            }
            if (!account.getStatus()) {
                Asserts.fail("用户已被冻结");
            }
            UmsAccountDetails accountDetails = getAccountDetails(account.getUsername());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(accountDetails,
                            null, accountDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            accountCacheService.setKey(account.getUsername(), accountDetails);
            token = jwtTokenService.generateToken(account.getUsername());
            log.info(accountDetails.getUsername() + "登录成功");
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }

        return token;
    }

    @Override
    public void logout(String authorization) {
        String accountName = jwtTokenService.getSubjectFromAuthorization(authorization);
        clearAccountStatus(accountName);
    }

    private void clearAccountStatus(String accountName) {
        accountCacheService.delKey(accountName);
    }

    private UmsAccountDetails getAccountDetailsNoCache(String accountName) {
        QueryWrapper<UmsAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", accountName);
        List<UmsAccount> umsAccounts = accountDao.selectList(queryWrapper);
        if (umsAccounts.size() == 0) {
            Asserts.fail("没有该用户名");
        }
        UmsAccount umsAccount = accountDao.selectById(umsAccounts.get(0));
        UmsAccount account = accountDao.selectById(umsAccount.getId());
        List<UmsResource> accountResources = getAccountResources(account.getId());
        return new UmsAccountDetails(account, accountResources);
    }

    private void refreshAccountDetailsCache(String accountName) {
        UmsAccountDetails accountDetailsNoCache = getAccountDetailsNoCache(accountName);
        accountCacheService.setKey(accountName, accountDetailsNoCache);
    }

    @Override
    public UmsAccountDetails getAccountDetails(String accountName) {
        //使用了缓存
        UmsAccountDetails accountDetails = accountCacheService.getKey(accountName);
        if (accountDetails != null) {
            return accountDetails;
        }
        accountDetails = getAccountDetailsNoCache(accountName);
        refreshAccountDetailsCache(accountName);
        return accountDetails;
    }


    @Override
    public String refreshToken(String authorization) {
        String accountName = jwtTokenService.getSubjectFromAuthorization(authorization);
        boolean b = accountCacheService.hasKey(accountName);
        if (!b) {
            Asserts.fail("用户未登陆");
        }
        String tokenFromAuthorization = jwtTokenService.getTokenFromAuthorization(authorization);
        String retToken = jwtTokenService.refreshHeadToken(tokenFromAuthorization);
        if (retToken == null) {
            Asserts.fail("token已经过期");
        }
        return retToken;
    }

    @Override
    public UmsAccount getAccountByAccountName(String accountName) {
        UmsAccountDetails accountDetails = getAccountDetails(accountName);
        return accountDetails.getAccount();
    }


    @Override
    public void updateAccount(UmsAccount newAccount) {
        int i = accountDao.updateById(newAccount);
        if (i == 0) {
            Asserts.fail("用户更新失败");
        }
        UmsAccount account = getAccounts(newAccount.getId());
        //刷新用户token，使用户需要重新登陆
        if (newAccount.getStatus() != null || newAccount.getRoleId() != null) {
            clearAccountStatus(account.getUsername());
        }
        refreshAccountDetailsCache(account.getUsername());
    }

    @Override
    public List<UmsAccount> getAccounts(UmsAccount account) {
        QueryWrapper<UmsAccount> accountQueryWrapper = new QueryWrapper<>(account);
        return accountDao.selectList(accountQueryWrapper);
    }

    @Override
    public UmsAccount getAccounts(Long accountId) {
        UmsAccount account = accountDao.selectById(accountId);
        if (account == null) {
            Asserts.fail("没有该用户");
        }
        return account;
    }


    @Override
    public void deleteAccountByAccountId(Long accountId) {
        UmsAccount account = getAccounts(accountId);
        clearAccountStatus(account.getUsername());
        int i = accountDao.deleteById(accountId);
        if (i == 0) {
            Asserts.fail("删除失败");
        }

    }

    @Override
    public void deleteAccounts(UmsAccount account) {
        List<UmsAccount> accounts = getAccounts(account);
        for (UmsAccount delAccount : accounts) {
            accountDao.deleteById(delAccount.getId());
        }
    }



    @Override
    public List<UmsAccount> list(PageInfo pageInfo, UmsAccount account) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return getAccounts(account);
    }

    @Override
    public List<UmsResource> getAccountResources(Long accountId) {
        UmsAccount account = accountDao.selectById(accountId);
        return roleService.getRoleResources(account.getRoleId());
    }

    @Override
    public UmsAccountWrapper getAccountByAuthorization(String authorization) {
        String accountName = jwtTokenService.getSubjectFromAuthorization(authorization);
        UmsAccount account = new UmsAccount();
        account.setUsername(accountName);
        UmsAccount umsAccount = accountDao.selectOne(new QueryWrapper<>(account));
        UmsRole role = roleService.getRole(umsAccount.getRoleId());
        UmsAccountWrapper accountWrapper = new UmsAccountWrapper();
        BeanUtil.copyProperties(umsAccount, accountWrapper);
        accountWrapper.setRole(role);
        return accountWrapper;
    }

    @Override
    public void addAccount(UmsAccount account) {
        if (!StringUtils.hasLength(account.getUsername())
                || !StringUtils.hasLength(account.getPassword())) {
            Asserts.fail("请您输入用户名和密码");
        }
        if (account.getRoleId() == null) {
            Asserts.fail("请您输入角色");
        }
        String encode = passwordEncoder.encode(account.getPassword());
        account.setPassword(encode);
        int i = accountDao.insert(account);
        if (i == 0) {
            Asserts.fail("插入失败");
        }
    }
}
