package com.hhoa.vblog.security.component;

import java.util.Map;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * 动态权限相关业务类.
 *
 * @author hhoa
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP.
     *
     * @return 数据
     */
    Map<AntPathRequestMatcher, ConfigAttribute> loadDataSource();
}
