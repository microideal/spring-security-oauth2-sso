package com.microideal.springsecurityoauth2sso.dto;

import com.microideal.springsecurityoauth2sso.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author microideal on 2018/9/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityDto {
    private Long id;

    private String name;

    private String value;

    private Integer type;

    private Long parentId;

    private Integer sort;

    public AuthorityDto(Authority authority){
        this.setName(authority.getName());
        this.setValue(authority.getValue());
        this.setParentId(authority.getParentId());
        this.setSort(authority.getSort());
        this.setType(authority.getType());
    }
}
