package com.microideal.springsecurityoauth2sso.dto;

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
 * @author microideal on 2018/9/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private Boolean enable;

    public UserDto(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setEnable(user.getEnable());
    }

    public static Page<UserDto> build(Page<User> userPage, Pageable pageable){
        List<UserDto> userDtos = new ArrayList<>();
        userPage.forEach(user -> userDtos.add(new UserDto(user)));
        return new PageImpl<>(userDtos,pageable,userPage.getTotalElements());
    }
}
