package com.microideal.springsecurityoauth2sso.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author microideal on 2018/9/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAddAuthoritiesCmd {
    private Long roleId;

    private List<Long> authorityIds;
}
