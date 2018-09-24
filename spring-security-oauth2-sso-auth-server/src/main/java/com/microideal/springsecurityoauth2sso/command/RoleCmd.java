package com.microideal.springsecurityoauth2sso.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author microideal on 2018/9/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleCmd {
    @NotNull
    private String name;
}
