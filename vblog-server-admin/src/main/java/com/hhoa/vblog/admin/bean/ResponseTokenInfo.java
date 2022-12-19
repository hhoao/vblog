package com.hhoa.vblog.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Response token info.
 *
 * @author hhoa
 * @since 2022 /9/6
 */
@Data
@AllArgsConstructor
public class ResponseTokenInfo {
    private String token;
    private String tokenHead;
}
