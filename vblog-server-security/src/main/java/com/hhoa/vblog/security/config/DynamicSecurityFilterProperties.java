package com.hhoa.vblog.security.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * DynamicSecurityFilterProperties.
 *
 * @author hhoa
 * @since 2022/12/23
 **/

@Data
public class DynamicSecurityFilterProperties {
    /**
     * 不需要验证的请求.
     */
    private List<String> ignored = new ArrayList<>();

    /**
     * 需要验证的请求.
     */
    private  List<String> include = new ArrayList<>();

    /**
     * 默认是否需要验证.
     */
    private Boolean authenticated = true;
}
