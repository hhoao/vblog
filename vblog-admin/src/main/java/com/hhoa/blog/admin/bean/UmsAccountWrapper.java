package com.hhoa.blog.admin.bean;

import com.hhoa.blog.mgb.model.UmsAccount;
import com.hhoa.blog.mgb.model.UmsRole;
import lombok.*;

/**
 * @author hhoa
 * @since 2022/9/9
 **/

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UmsAccountWrapper extends UmsAccount {
    private UmsRole role;
}
