package com.microideal.springsecurityoauth2sso.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;

/**
 * @author microideal on 2018/9/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCmd {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public void passwordEncode(PasswordEncoder passwordEncoder){
        this.setPassword(passwordEncoder.encode(this.getPassword()));
    }
}
