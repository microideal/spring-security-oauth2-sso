package com.microideal.springsecurityoauth2sso.service.impl;

import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.command.UserCmd;
import com.microideal.springsecurityoauth2sso.constant.AuthorityType;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.User;
import com.microideal.springsecurityoauth2sso.dto.AuthorityDto;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.UserDto;
import com.microideal.springsecurityoauth2sso.queryobject.UserQueryObject;
import com.microideal.springsecurityoauth2sso.repository.AuthorityRepository;
import com.microideal.springsecurityoauth2sso.repository.UserRepository;
import com.microideal.springsecurityoauth2sso.service.AuthorityService;
import com.microideal.springsecurityoauth2sso.service.UserService;
import com.microideal.springsecurityoauth2sso.utils.ExampleMatcherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author microideal on 2018/9/20
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Page<UserDto> query(UserQueryObject queryObject, Pageable pageable) {
        Example<User> userExample = Example.of(new User(queryObject), ExampleMatcherUtils.generateStringContainingAndNullIgnoreMatcher());
        Page<User> userPage = userRepository.findAll(userExample,pageable);
        return UserDto.build(userPage,pageable);
    }

    @Override
    public List<AuthorityDto> queryLeftMenu(String username) {
        List<AuthorityDto> authorityDtos = new ArrayList<>();
        Set<Long> authorityIds = new HashSet<>();
        User user = userRepository.findByUsername(username);
        user.getRoles().forEach(role -> {
            role.getAuthorities().forEach(authority -> authorityIds.add(authority.getId()));//会有重复的权限，过滤掉
        });
        authorityIds.forEach(id ->{
            Authority authority = authorityRepository.findById(id).get();
            if(AuthorityType.LEVEL_1_MENU.equals(authority.getType())){
                authorityDtos.add(new AuthorityDto(authority));
            }
        });
        authorityDtos.sort(Comparator.comparing(AuthorityDto::getSort));
        return authorityDtos;
    }

    @Override
    public Msg save(UserCmd cmd) {
        cmd.passwordEncode(passwordEncoder);
        User user = userRepository.findByUsername(cmd.getUsername());
        if (user != null){
            return new Msg<>(MessageType.MSG_TYPE_FAILURE,"该用户名已经存在",null);
        }
        userRepository.save(new User(cmd));
        return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Msg addUserRoles(UserAddRolesCmd cmd) {
        User user = userRepository.findById(cmd.getUserId()).get();
        user.update(cmd);
        userRepository.save(user);
        return new Msg<>(MessageType.MSG_TYPE_SUCCESS, null, null);
    }

}
