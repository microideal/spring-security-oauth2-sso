package com.microideal.springsecurityoauth2sso.queryobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author microideal on 2018/9/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryObject {
    private Long id;

    private String name;
}
