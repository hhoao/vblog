package com.hhoa.blog.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hhoa
 * @since 2022/9/6
 **/

@Data
@AllArgsConstructor
public class ResponseTokenInfo {
    private String token;
    private String tokenHead;
}
