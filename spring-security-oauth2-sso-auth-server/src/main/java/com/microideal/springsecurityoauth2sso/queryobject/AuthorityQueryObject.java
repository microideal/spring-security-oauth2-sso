package com.microideal.springsecurityoauth2sso.queryobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author microideal on 2018/9/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityQueryObject {
    private Long id;

    private String name;
}
