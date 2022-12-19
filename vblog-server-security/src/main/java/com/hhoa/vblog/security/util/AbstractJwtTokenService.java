package com.hhoa.vblog.security.util;

import lombok.Data;

/**
 * AbstractJwtTokenService.
 *
 * @author hhoa
 * @since  2022/5/14
 **/

@Data
public abstract class AbstractJwtTokenService implements JwtTokenService {
    private String secret;
    private Long expiration;
    private Integer refreshTime;
    private String tokenHead;
}
