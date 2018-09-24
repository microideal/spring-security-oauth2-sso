package com.microideal.springsecurityoauth2sso.domain;

import com.microideal.springsecurityoauth2sso.command.RoleAddAuthoritiesCmd;
import com.microideal.springsecurityoauth2sso.queryobject.RoleQueryObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class Role implements Serializable{

    private static final long serialVersionUID = 3527851934255598211L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(targetEntity = Authority.class,fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Set<Authority> authorities = new HashSet<>();

    public Role(Long id){
        this.setId(id);
    }

    public Role(String name){
        this.setName(name);
    }

    public static Set<Role> build(List<Long> roleIds){
        Set<Role> set = new HashSet<>();
        roleIds.forEach(roleId ->{
            set.add(new Role(roleId));
        });
        return set;
    }

    public Role(RoleQueryObject queryObject){
        this.setId(queryObject.getId());
        this.setName(queryObject.getName());
    }

    public void update(RoleAddAuthoritiesCmd cmd){
        this.setAuthorities(Authority.build(cmd.getAuthorityIds()));
    }

}
