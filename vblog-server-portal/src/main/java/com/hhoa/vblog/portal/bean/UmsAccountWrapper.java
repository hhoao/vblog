package com.hhoa.vblog.portal.bean;

import com.hhoa.vblog.mgb.model.UmsAccount;
import com.hhoa.vblog.mgb.model.UmsRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Ums account wrapper.
 *
 * @author hhoa
 * @since 2022 /9/9
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UmsAccountWrapper extends UmsAccount {
    private UmsRole role;
}
