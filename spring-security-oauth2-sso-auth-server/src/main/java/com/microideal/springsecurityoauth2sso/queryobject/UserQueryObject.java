package com.microideal.springsecurityoauth2sso.queryobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author microideal on 2018/9/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryObject {

    private Long id;

    private String username;

    private Boolean enable;
}
