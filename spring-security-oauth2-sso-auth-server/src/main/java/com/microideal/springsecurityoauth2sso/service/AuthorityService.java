package com.microideal.springsecurityoauth2sso.service;

import com.microideal.springsecurityoauth2sso.command.AuthorityCmd;
import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.projection.RoleAuthorityWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.queryobject.AuthorityQueryObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
public interface AuthorityService {
    List<RoleAuthorityWithSelected> queryRoleAuthorityWithSelectedByRoleId(Long roleId);

    Page<Authority> query(AuthorityQueryObject queryObject, Pageable pageable);

    Msg delete(Long id);

    Msg add(AuthorityCmd cmd);
}
