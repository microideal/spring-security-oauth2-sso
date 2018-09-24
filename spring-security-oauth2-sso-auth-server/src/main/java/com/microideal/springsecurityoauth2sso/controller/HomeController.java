package com.microideal.springsecurityoauth2sso.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author microideal on 2018/9/18
 */
@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        // 迭代查找并清除Cookie
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        try {
            response.sendRedirect(request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return "redirect:/login";
    }

    @RequestMapping("/usersManager")
    @PreAuthorize("hasAuthority('/usersManager')")
    public String usersPage(){
        return "user/users";
    }

    @RequestMapping("/rolesManager")
    @PreAuthorize("hasAuthority('/rolesManager')")
    public String rolesPage(){
        return "role/roles";
    }

    @RequestMapping("/authoritiesManager")
    @PreAuthorize("hasAuthority('/authoritiesManager')")
    public String resourcesPage(){
        return "authorities/authorities";
    }


}
