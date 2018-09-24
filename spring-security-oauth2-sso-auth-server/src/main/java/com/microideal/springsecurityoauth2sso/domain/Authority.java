package com.microideal.springsecurityoauth2sso.domain;

import com.microideal.springsecurityoauth2sso.command.AuthorityCmd;
import com.microideal.springsecurityoauth2sso.queryobject.AuthorityQueryObject;
import com.microideal.springsecurityoauth2sso.queryobject.RoleQueryObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author microideal on 2018/9/20
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements Serializable{
    private static final long serialVersionUID = -2060198575621907406L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String value;

    private Integer type;

    private Long parentId;

    private Integer sort;

    public Authority(Long authorityId){
        this.setId(authorityId);
    }

    public Authority(AuthorityQueryObject queryObject){
        this.setId(queryObject.getId());
        this.setName(queryObject.getName());
    }

    public static Set<Authority> build(List<Long> authorityIds){
        Set<Authority> set = new HashSet<>();
        authorityIds.forEach(authorityId ->{
            set.add(new Authority(authorityId));
        });
        return set;
    }

    public Authority(AuthorityCmd cmd){
        this.setName(cmd.getName());
        this.setValue(cmd.getValue());
        this.setParentId(cmd.getParentId());
        this.setSort(cmd.getSort());
        this.setType(cmd.getType());
    }
}
