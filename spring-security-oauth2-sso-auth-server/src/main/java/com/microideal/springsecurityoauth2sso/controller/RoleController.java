package com.microideal.springsecurityoauth2sso.controller;

import com.microideal.springsecurityoauth2sso.command.RoleAddAuthoritiesCmd;
import com.microideal.springsecurityoauth2sso.command.RoleCmd;
import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.constant.MessageType;
import com.microideal.springsecurityoauth2sso.domain.projection.UserRoleWithSelected;
import com.microideal.springsecurityoauth2sso.dto.Msg;
import com.microideal.springsecurityoauth2sso.dto.RoleDto;
import com.microideal.springsecurityoauth2sso.queryobject.RoleQueryObject;
import com.microideal.springsecurityoauth2sso.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/queryUserRoleWithSelected")
    public ResponseEntity<Msg<List<UserRoleWithSelected>>> queryUserRoleWithSelected(Long userId){
        Msg<List<UserRoleWithSelected>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,roleService.queryUserRoleWithSelectedByUserId(userId));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/query")
    @PreAuthorize("hasAuthority('/rolesManager')")
    public ResponseEntity<Msg<Page<RoleDto>>> query(RoleQueryObject queryObject, Pageable pageable){
        Msg<Page<RoleDto>> msg = new Msg<>(MessageType.MSG_TYPE_SUCCESS,null,roleService.query(queryObject,pageable));
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('/roles/add')")
    public ResponseEntity<Msg> save(@Valid @RequestBody RoleCmd cmd){
        return new ResponseEntity<>(roleService.save(cmd),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('/roles/delete')")
    public ResponseEntity<Msg> delete(@PathVariable Long id){
        return new ResponseEntity<>(roleService.delete(id),HttpStatus.OK);
    }

    @PostMapping("/addRoleAuthority")
    @PreAuthorize("hasAuthority('/roles/addRoleAuthorities')")
    public ResponseEntity<Msg> addRoleAuthority(@RequestBody RoleAddAuthoritiesCmd cmd){
        return new ResponseEntity<>(roleService.addRoleAuthority(cmd),HttpStatus.OK);
    }
}
