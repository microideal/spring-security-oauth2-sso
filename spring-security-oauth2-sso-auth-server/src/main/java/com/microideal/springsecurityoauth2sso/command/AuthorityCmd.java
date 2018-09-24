package com.microideal.springsecurityoauth2sso.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author microideal on 2018/9/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityCmd {
    @NotNull
    private String name;

    @NotNull
    private String value;

    @NotNull
    private Integer type;

    @NotNull
    private Long parentId;

    @NotNull
    private Integer sort;
}
