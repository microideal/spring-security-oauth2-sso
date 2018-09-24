package com.microideal.springsecurityoauth2sso.domain;

import com.microideal.springsecurityoauth2sso.command.UserAddRolesCmd;
import com.microideal.springsecurityoauth2sso.command.UserCmd;
import com.microideal.springsecurityoauth2sso.queryobject.UserQueryObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 2060191175621907416L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(length = 20,unique = true,nullable = false)
    private String username;

    @NotNull
    @Column(length = 60)
    private String password;

    @NotNull
    private Boolean enable;

    @ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private Set<Role> roles = new HashSet<>();

    @Transient
    private Set<GrantedAuthority> authorities = new HashSet<>();


    public Set<GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> userAuthorities = new HashSet<>();
        this.roles.forEach(role -> {
            role.getAuthorities().forEach(authority -> {
                userAuthorities.add(new SimpleGrantedAuthority(authority.getValue()));
            });
        });
        return userAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(UserQueryObject queryObject){
        this.setId(queryObject.getId());
        this.setUsername(queryObject.getUsername());
        this.setEnable(queryObject.getEnable());
    }

    public User(UserCmd cmd){
        this.setUsername(cmd.getUsername());
        this.setPassword(cmd.getPassword());
        this.setEnable(true);
    }

    public void update(UserAddRolesCmd cmd){
        this.setRoles(Role.build(cmd.getRoleIds()));
    }

}
