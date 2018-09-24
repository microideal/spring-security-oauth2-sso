package com.microideal.springsecurityoauth2sso.dto;

import com.microideal.springsecurityoauth2sso.domain.Role;
import com.microideal.springsecurityoauth2sso.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author microideal on 2018/9/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;

    private String name;

    public RoleDto(Role role){
        this.setId(role.getId());
        this.setName(role.getName());
    }

    public static Page<RoleDto> build(Page<Role> rolePage, Pageable pageable){
        List<RoleDto> roleDtos = new ArrayList<>();
        rolePage.forEach(role -> roleDtos.add(new RoleDto(role)));
        return new PageImpl<>(roleDtos,pageable,rolePage.getTotalElements());
    }
}
