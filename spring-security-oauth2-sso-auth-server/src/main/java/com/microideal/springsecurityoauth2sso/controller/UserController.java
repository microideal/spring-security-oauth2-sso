package com.microideal.springsecurityoauth2sso.controller;

import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.command.UserCmd;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.User;
import com.microideal.springsecurityoauth2sso.dto.AuthorityDto;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.UserDto;
import com.microideal.springsecurityoauth2sso.queryobject.UserQueryObject;
import com.microideal.springsecurityoauth2sso.repository.UserRepository;
import com.microideal.springsecurityoauth2sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

/**
 * @author microideal on 2018/9/20
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*@RequestMapping("/userInfo")
    public Principal user(Principal principal) {
        return principal;
    }*/

    @GetMapping
    @ResponseBody
    @PreAuthorize("hasAuthority('authorizationSetting')")
    public ResponseEntity<Msg<List<AuthorityDto>>> queryLeftMenu(Principal principal) {
        Msg<List<AuthorityDto>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,userService.queryLeftMenu(principal.getName()));
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('/usersManager')")
    public ResponseEntity<Msg<Page<UserDto>>> query(UserQueryObject queryObject, Pageable pageable){
        Msg<Page<UserDto>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,userService.query(queryObject,pageable));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('/users/add')")
    public ResponseEntity<Msg> save(@Valid @RequestBody UserCmd cmd){
        return new ResponseEntity<>(userService.save(cmd),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('/users/delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> delete(@PathVariable Long id){
        userService.delete(id);
        Msg msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,null);
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @PostMapping("/addUserRoles")
    @PreAuthorize("hasAuthority('/users/addUserRoles')")
    public ResponseEntity<Msg> addUserRoles(@RequestBody UserAddRolesCmd cmd){
        return new ResponseEntity<>(userService.addUserRoles(cmd),HttpStatus.OK);
    }
}
