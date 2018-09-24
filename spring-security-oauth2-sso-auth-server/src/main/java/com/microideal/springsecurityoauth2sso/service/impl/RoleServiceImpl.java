package com.microideal.springsecurityoauth2sso.service.impl;

import com.microideal.springsecurityoauth2sso.command.RoleAddAuthoritiesCmd;
import com.microideal.springsecurityoauth2sso.command.RoleCmd;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.Role;
import com.microideal.springsecurityoauth2sso.domain.projection.UserRoleWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.RoleDto;
import com.microideal.springsecurityoauth2sso.queryobject.RoleQueryObject;
import com.microideal.springsecurityoauth2sso.repository.RoleRepository;
import com.microideal.springsecurityoauth2sso.service.RoleService;
import com.microideal.springsecurityoauth2sso.utils.ExampleMatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRoleWithSelected> queryUserRoleWithSelectedByUserId(Long userId) {
        return roleRepository.queryUserRoleWithSelectedByUserId(userId);
    }

    @Override
    public Page<RoleDto> query(RoleQueryObject queryObject, Pageable pageable) {
        Example<Role> roleExample = Example.of(new Role(queryObject), ExampleMatcherUtils.generateStringContainingAndNullIgnoreMatcher());
        Page<Role> rolePage = roleRepository.findAll(roleExample,pageable);
        return RoleDto.build(rolePage,pageable);
    }

    @Override
    public Msg save(RoleCmd cmd) {
        roleRepository.save(new Role(cmd.getName()));
        return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
    }

    @Override
    public Msg delete(Long id) {
        Integer count = roleRepository.queryUserRole(id);
        if(count > 0){
            return new Msg<>(MessageType.MSG_TYPE_FAILURE,"该角色已经分配给用户，不可删除！",null);
        }else {
            roleRepository.deleteById(id);
            return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
        }

    }

    @Override
    public Msg addRoleAuthority(RoleAddAuthoritiesCmd cmd) {
        Role role = roleRepository.findById(cmd.getRoleId()).get();
        role.update(cmd);
        roleRepository.save(role);
        return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
    }
}
