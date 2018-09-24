package com.microideal.springsecurityoauth2sso.service;

import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.command.UserCmd;
import com.microideal.springsecurityoauth2sso.dto.AuthorityDto;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.UserDto;
import com.microideal.springsecurityoauth2sso.queryobject.UserQueryObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author microideal on 2018/9/20
 */
public interface UserService {
    Page<UserDto> query(UserQueryObject queryObject, Pageable pageable);

    List<AuthorityDto> queryLeftMenu(String username);

    Msg save(UserCmd cmd);

    void delete(Long id);

    Msg addUserRoles(UserAddRolesCmd cmd);
}
