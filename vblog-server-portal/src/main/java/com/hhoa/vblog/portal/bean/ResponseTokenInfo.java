package com.hhoa.vblog.portal.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 响应的Token信息.
 *
 * @author hhoa
 * @since 2022/9/6
 **/

@Data
@AllArgsConstructor
public class ResponseTokenInfo {
    private String token;
    private String tokenHead;
}
