package com.hhoa.blog.admin.service.impl;

import com.github.pagehelper.PageHelper;

import com.hhoa.blog.admin.bean.PageInfo;
import com.hhoa.blog.admin.bean.UmsAdministratorDetails;
import com.hhoa.blog.admin.bean.UmsLoginParam;
import com.hhoa.blog.admin.service.UmsAdministratorCacheService;
import com.hhoa.blog.admin.service.UmsAdministratorService;
import com.hhoa.blog.admin.service.UmsRoleService;
import com.hhoa.blog.mgb.mapper.UmsAdministratorMapper;
import com.hhoa.blog.mgb.model.UmsAdministrator;
import com.hhoa.blog.mgb.model.UmsAdministratorExample;
import com.hhoa.blog.mgb.model.UmsMenu;
import com.hhoa.blog.mgb.model.UmsResource;
import com.hhoa.blog.common.exception.Asserts;
import com.hhoa.blog.security.util.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * The type Ums administrator service.
 *
 * @author hhoa
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UmsAdministratorServiceImpl implements UmsAdministratorService {
    private final UmsAdministratorMapper administratorMapper;
    private final PasswordEncoder passwordEncoder;
    private final UmsAdministratorCacheService administratorCacheService;

    private final JwtTokenService jwtTokenService;
    private final UmsRoleService roleService;

    @Override
    public String login(UmsLoginParam loginParam) {
        String token = null;
        try {
            UmsAdministrator administrator = getAdministratorByAdministratorName(loginParam.getIdentifier());
            if (!passwordEncoder.matches(loginParam.getPassword(), administrator.getPassword())) {
                Asserts.fail("密码错误");
            }
            if (!administrator.getStatus().equals(1)) {
                throw new DisabledException("用户已被冻结");
            }
            UmsAdministratorDetails administratorDetails = getAdministratorDetails(administrator.getUsername());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(administratorDetails, null, administratorDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            administratorCacheService.setKey(administrator.getUsername(), administratorDetails);
            token = jwtTokenService.generateToken(administrator.getUsername());
            log.info(administratorDetails.getUsername() + "登录成功");
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }

        return token;
    }

    @Override
    public void logout(String authorization) {
        String administratorName = jwtTokenService.getSubjectFromAuthorization(authorization);
        clearAdministratorStatus(administratorName);
    }

    private void clearAdministratorStatus(String administratorName) {
        administratorCacheService.delKey(administratorName);
    }

    private UmsAdministratorDetails getAdministratorDetailsNoCache(String administratorName) {
        UmsAdministratorExample administratorExample = new UmsAdministratorExample();
        UmsAdministratorExample.Criteria criteria = administratorExample.createCriteria();
        criteria.andUsernameEqualTo(administratorName);
        List<UmsAdministrator> retAdministrators = administratorMapper.selectByExample(administratorExample);
        if (retAdministrators.size() == 0){
            Asserts.fail("没有该用户名");
        }
        UmsAdministrator administratorByAdministratorName = retAdministrators.get(0);
        UmsAdministrator administrator = administratorMapper.selectByPrimaryKey(administratorByAdministratorName.getId());
        List<UmsResource> administratorResources = getAdministratorResources(administrator.getId());
        return new UmsAdministratorDetails(administrator, administratorResources);
    }

    private void refreshAdministratorDetailsCache(String administratorName) {
        UmsAdministratorDetails administratorDetailsNoCache = getAdministratorDetailsNoCache(administratorName);
        administratorCacheService.setKey(administratorName, administratorDetailsNoCache);
    }

    @Override
    public UmsAdministratorDetails getAdministratorDetails(String administratorName) {
        //使用了缓存
        UmsAdministratorDetails administratorDetails = administratorCacheService.getKey(administratorName);
        if (administratorDetails != null) {
            return administratorDetails;
        }
        administratorDetails = getAdministratorDetailsNoCache(administratorName);
        refreshAdministratorDetailsCache(administratorName);
        return administratorDetails;
    }


    @Override
    public String refreshToken(String authorization) {
        String administratorName = jwtTokenService.getSubjectFromAuthorization(authorization);
        boolean b = administratorCacheService.hasKey(administratorName);
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
    public UmsAdministrator getAdministratorByAdministratorName(String administratorName) {
        UmsAdministratorDetails administratorDetails = getAdministratorDetails(administratorName);
        return administratorDetails.getAdministrator();
    }


    @Override
    public void updateAdministrator(UmsAdministrator newAdministrator) {
        int i = administratorMapper.updateByPrimaryKeySelective(newAdministrator);
        if (i == 0) {
            Asserts.fail("用户更新失败");
        }
        UmsAdministrator administrator = getAdministrators(newAdministrator.getId());
        //刷新用户token，使用户需要重新登陆
        if (newAdministrator.getStatus() != null || newAdministrator.getRoleId() != null) {
            clearAdministratorStatus(administrator.getUsername());
        }
        refreshAdministratorDetailsCache(administrator.getUsername());
    }

    @Override
    public UmsAdministrator getAdministrators(Long administratorId) {
        UmsAdministrator administrator = administratorMapper.selectByPrimaryKey(administratorId);
        if (administrator == null) {
            Asserts.fail("没有该用户");
        }
        return administrator;
    }


    @Override
    public void deleteAdministratorByAdministratorId(Long administratorId) {
        UmsAdministrator administrator = getAdministrators(administratorId);
        clearAdministratorStatus(administrator.getUsername());
        int i = administratorMapper.deleteByPrimaryKey(administratorId);
        if (i == 0) {
            Asserts.fail("删除失败");
        }

    }

    @Override
    public void deleteAdministrators(UmsAdministrator administrator) {
        List<UmsAdministrator> administrators = getAdministrators(administrator);
        for (UmsAdministrator delAdministrator : administrators) {
            administratorMapper.deleteByPrimaryKey(delAdministrator.getId());
        }
    }

    /**
     * 获取administratorExample
     *
     * @param administrator administrator
     * @return UmsAdministratorExample
     */
    private UmsAdministratorExample getAdministratorExample(UmsAdministrator administrator) {
        UmsAdministratorExample administratorExample = new UmsAdministratorExample();
        if (administrator != null) {
            UmsAdministratorExample.Criteria criteria = administratorExample.createCriteria();
            if (administrator.getId() != null)
                criteria.andIdEqualTo(administrator.getId());
            if (StringUtils.hasLength(administrator.getUsername()))
                criteria.andUsernameEqualTo(administrator.getUsername());
            if (administrator.getStatus() != null)
                criteria.andStatusEqualTo(administrator.getStatus());
            if (administrator.getRoleId() != null)
                criteria.andRoleIdEqualTo(administrator.getRoleId());
        }
        return administratorExample;
    }

    @Override
    public List<UmsAdministrator> getAdministrators(UmsAdministrator administrator) {
        UmsAdministratorExample administratorExample = getAdministratorExample(administrator);
        return administratorMapper.selectByExample(administratorExample);
    }

    @Override
    public List<UmsAdministrator> list(PageInfo pageInfo, UmsAdministrator administrator) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        return getAdministrators(administrator);
    }

    @Override
    public List<UmsResource> getAdministratorResources(Long administratorId) {
        UmsAdministrator administrator = administratorMapper.selectByPrimaryKey(administratorId);
        return roleService.getRoleResources(administrator.getRoleId());
    }

    @Override
    public UmsAdministrator getAdministratorByAuthorization(String authorization) {
        String administratorName = jwtTokenService.getSubjectFromAuthorization(authorization);
        return this.getAdministratorByAdministratorName(administratorName);
    }

    @Override
    public List<UmsMenu> getMenusByAuthorization(String authorization) {
        String administratorName = jwtTokenService.getSubjectFromAuthorization(authorization);
        UmsAdministrator administratorByName = this.getAdministratorByAdministratorName(administratorName);
        return roleService.getMenus(administratorByName.getRoleId());
    }

    @Override
    public void addAdministrator(UmsAdministrator administrator) {
        if (!StringUtils.hasLength(administrator.getUsername()) || !StringUtils.hasLength(administrator.getPassword())){
            Asserts.fail("请您输入用户名和密码");
        }
        if (administrator.getRoleId() == null){
            Asserts.fail("请您输入角色");
        }
        String encode = passwordEncoder.encode(administrator.getPassword());
        administrator.setPassword(encode);
        int i = administratorMapper.insertSelective(administrator);
        if (i == 0){
            Asserts.fail("插入失败");
        }
    }
}
