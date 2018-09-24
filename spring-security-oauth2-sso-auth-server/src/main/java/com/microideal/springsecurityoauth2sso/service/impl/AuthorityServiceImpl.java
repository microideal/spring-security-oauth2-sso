package com.microideal.springsecurityoauth2sso.service.impl;

import com.microideal.springsecurityoauth2sso.command.AuthorityCmd;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.projection.RoleAuthorityWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.queryobject.AuthorityQueryObject;
import com.microideal.springsecurityoauth2sso.repository.AuthorityRepository;
import com.microideal.springsecurityoauth2sso.service.AuthorityService;
import com.microideal.springsecurityoauth2sso.utils.ExampleMatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author microideal on 2018/9/21
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<RoleAuthorityWithSelected> queryRoleAuthorityWithSelectedByRoleId(Long roleId) {
        return authorityRepository.queryRoleAuthorityWithSelectedByRoleId(roleId);
    }

    @Override
    public Page<Authority> query(AuthorityQueryObject queryObject, Pageable pageable) {
        Example<Authority> roleExample = Example.of(new Authority(queryObject), ExampleMatcherUtils.generateStringContainingAndNullIgnoreMatcher());
        Page<Authority> authorityPage = authorityRepository.findAll(roleExample,pageable);
        return authorityPage;
    }

    @Override
    public Msg delete(Long authorityId) {
        Integer count = authorityRepository.queryRoleAuthority(authorityId);
        if(count > 0){
            return new Msg<>(MessageType.MSG_TYPE_FAILURE,"该权限已经分配给角色，不可删除！",null);
        }else {
            authorityRepository.deleteById(authorityId);
            return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
        }
    }

    @Override
    public Msg add(AuthorityCmd cmd) {
        Optional<Authority> authorityOptional = authorityRepository.findById(cmd.getParentId());
        if(authorityOptional.isPresent()){
            authorityRepository.save(new Authority(cmd));
            return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
        }else {
            return new Msg<>(MessageType.MSG_TYPE_FAILURE, "父菜单不存在", null);
        }
    }
}
