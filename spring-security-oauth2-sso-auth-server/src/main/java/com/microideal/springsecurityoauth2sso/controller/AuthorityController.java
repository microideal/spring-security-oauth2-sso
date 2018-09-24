package com.microideal.springsecurityoauth2sso.controller;

import com.microideal.springsecurityoauth2sso.command.AuthorityCmd;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.Authority;
import com.microideal.springsecurityoauth2sso.domain.projection.RoleAuthorityWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.queryobject.AuthorityQueryObject;
import com.microideal.springsecurityoauth2sso.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @GetMapping("/queryRoleAuthorityWithSelectedByRoleId")
    public ResponseEntity<Msg<List<RoleAuthorityWithSelected>>> queryRoleAuthorityWithSelectedByRoleId(Long roleId){
        Msg<List<RoleAuthorityWithSelected>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,authorityService.queryRoleAuthorityWithSelectedByRoleId(roleId));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('/authoritiesManager')")
    public ResponseEntity<Msg<Page<Authority>>> query(AuthorityQueryObject queryObject, Pageable pageable){
        Msg<Page<Authority>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,authorityService.query(queryObject,pageable));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('/authorities/delete')")
    public ResponseEntity<Msg> delete(@PathVariable Long id){
        return new ResponseEntity<>(authorityService.delete(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('/roles/addRoleAuthorities')")
    public ResponseEntity<Msg> add(@RequestBody AuthorityCmd cmd){
        return new ResponseEntity<>(authorityService.add(cmd),HttpStatus.OK);
    }

}
