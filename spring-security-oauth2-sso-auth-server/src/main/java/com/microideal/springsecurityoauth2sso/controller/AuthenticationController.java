package com.microideal.springsecurityoauth2sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author microideal on 2018/9/22
 */
@RestController
public class AuthenticationController {
    /**
     * 只能用这么写，不然权限过不去，换个url都不行
     * @param principal
     * @return
     */
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
