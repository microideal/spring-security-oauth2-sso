package com.microideal.SpringSecurityOauth2SsoClient1.controller;

import com.microideal.SpringSecurityOauth2SsoClient1.domain.Member;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author microideal on 2018/9/18
 */
@Controller
@RequestMapping("/member")
public class MemberController {

    @RequestMapping(value="/logout")
    public String logout() {
        return "redirect:http://localhost:8080/logout";
        //return "redirect:/login";
    }

    //登陆后可访问
    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    /**
     * 会员列表页面
     * 有/usersManager权限可访问
     */
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('/usersManager')")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("member/list");
        return modelAndView;
    }

    //有superAdmin权限可访问
    @GetMapping("/hello")
    @ResponseBody
    @PreAuthorize("hasAuthority('superAdmin')")
    public String hello(){
        return "hello microideal";
    }

}
