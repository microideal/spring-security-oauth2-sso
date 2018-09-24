package com.microideal.SpringSecurityOauth2SsoClient2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @RequestMapping(value="/logout")
    public String logout() {
        return "redirect:http://localhost:8080/logout";
        //return "redirect:/login";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }

    /**
     * 会员列表页面
     */
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('/usersManager')")
    public ModelAndView list() {
        return new ModelAndView("coupon/list");
    }

    @GetMapping("/hello")
    @ResponseBody
    @PreAuthorize("hasAuthority('superAdmin')")
    public String hello(){
        return "hello microideal";
    }

}
