package com.microideal.springsecurityoauth2sso.service;

import com.microideal.springsecurityoauth2sso.command.RoleAddAuthoritiesCmd;
import com.microideal.springsecurityoauth2sso.command.RoleCmd;
import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.domain.projection.UserRoleWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.RoleDto;
import com.microideal.springsecurityoauth2sso.queryobject.RoleQueryObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
public interface RoleService {
    List<UserRoleWithSelected> queryUserRoleWithSelectedByUserId(Long userId);

    Page<RoleDto> query(RoleQueryObject queryObject, Pageable pageable);

    Msg save(RoleCmd cmd);

    Msg delete(Long id);

    Msg addRoleAuthority(RoleAddAuthoritiesCmd cmd);
}
