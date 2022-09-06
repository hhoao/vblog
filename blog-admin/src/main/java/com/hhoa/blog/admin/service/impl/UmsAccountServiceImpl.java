package com.hhoa.blog.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsAccountDetails;
import com.hhoa.blog.admin.bean.UmsLoginParam;
import com.hhoa.blog.admin.service.UmsAccountCacheService;
import com.hhoa.blog.admin.service.UmsAccountService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.common.exception.Asserts;
import com.hhoa.blog.mgb.mapper.UmsAccountMapper;
import com.hhoa.blog.mgb.model.UmsAccount;
import com.hhoa.blog.mgb.model.UmsAccountExample;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.security.util.JwtTokenService;
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
    private final UmsAccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final UmsAccountCacheService accountCacheService;

    private final JwtTokenService jwtTokenService;
    private final UmsRoleService roleService;

    @Override
    public String login(UmsLoginParam loginParam) {
        String token = null;
        try {
            UmsAccount account = getAccountByAccountName(loginParam.getIdentifier());
            if (!passwordEncoder.matches(loginParam.getPassword(), account.getPassword())) {
                Asserts.fail("密码错误");
            }
            if (!account.getStatus()) {
                Asserts.fail("用户已被冻结");
            }
            UmsAccountDetails accountDetails = getAccountDetails(account.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(accountDetails, null, accountDetails.getAuthorities());
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
        UmsAccountExample accountExample = new UmsAccountExample();
        UmsAccountExample.Criteria criteria = accountExample.createCriteria();
        criteria.andUsernameEqualTo(accountName);
        List<UmsAccount> retAccounts = accountMapper.selectByExample(accountExample);
        if (retAccounts.size() == 0) {
            Asserts.fail("没有该用户名");
        }
        UmsAccount accountByAccountName = retAccounts.get(0);
        UmsAccount account = accountMapper.selectByPrimaryKey(accountByAccountName.getId());
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
        int i = accountMapper.updateByPrimaryKeySelective(newAccount);
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
    public UmsAccount getAccounts(Long accountId) {
        UmsAccount account = accountMapper.selectByPrimaryKey(accountId);
        if (account == null) {
            Asserts.fail("没有该用户");
        }
        return account;
    }


    @Override
    public void deleteAccountByAccountId(Long accountId) {
        UmsAccount account = getAccounts(accountId);
        clearAccountStatus(account.getUsername());
        int i = accountMapper.deleteByPrimaryKey(accountId);
        if (i == 0) {
            Asserts.fail("删除失败");
        }

    }

    @Override
    public void deleteAccounts(UmsAccount account) {
        List<UmsAccount> accounts = getAccounts(account);
        for (UmsAccount delAccount : accounts) {
            accountMapper.deleteByPrimaryKey(delAccount.getId());
        }
    }

    /**
     * 获取accountExample
     *
     * @param account account
     * @return UmsAccountExample
     */
    private UmsAccountExample getAccountExample(UmsAccount account) {
        UmsAccountExample accountExample = new UmsAccountExample();
        if (account != null) {
            UmsAccountExample.Criteria criteria = accountExample.createCriteria();
            if (account.getId() != null)
                criteria.andIdEqualTo(account.getId());
            if (StringUtils.hasLength(account.getUsername()))
                criteria.andUsernameEqualTo(account.getUsername());
            if (account.getStatus() != null)
                criteria.andStatusEqualTo(account.getStatus());
            if (account.getRoleId() != null)
                criteria.andRoleIdEqualTo(account.getRoleId());
        }
        return accountExample;
    }

    @Override
    public List<UmsAccount> getAccounts(UmsAccount account) {
        UmsAccountExample accountExample = getAccountExample(account);
        return accountMapper.selectByExample(accountExample);
    }

    @Override
    public List<UmsAccount> list(PageInfo pageInfo, UmsAccount account) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return getAccounts(account);
    }

    @Override
    public List<UmsResource> getAccountResources(Long accountId) {
        UmsAccount account = accountMapper.selectByPrimaryKey(accountId);
        return roleService.getRoleResources(account.getRoleId());
    }

    @Override
    public UmsAccount getAccountByAuthorization(String authorization) {
        String accountName = jwtTokenService.getSubjectFromAuthorization(authorization);
        return this.getAccountByAccountName(accountName);
    }

    @Override
    public List<UmsMenu> getMenusByAuthorization(String authorization) {
        String accountName = jwtTokenService.getSubjectFromAuthorization(authorization);
        UmsAccount accountByName = this.getAccountByAccountName(accountName);
        return roleService.getMenus(accountByName.getRoleId());
    }

    @Override
    public void addAccount(UmsAccount account) {
        if (!StringUtils.hasLength(account.getUsername()) || !StringUtils.hasLength(account.getPassword())) {
            Asserts.fail("请您输入用户名和密码");
        }
        if (account.getRoleId() == null) {
            Asserts.fail("请您输入角色");
        }
        String encode = passwordEncoder.encode(account.getPassword());
        account.setPassword(encode);
        int i = accountMapper.insertSelective(account);
        if (i == 0) {
            Asserts.fail("插入失败");
        }
    }
}
