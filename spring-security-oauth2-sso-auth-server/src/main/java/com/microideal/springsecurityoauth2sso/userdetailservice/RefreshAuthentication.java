//package com.microideal.springsecurityoauth2sso.userdetailservice;
//
//import com.microideal.springsecurityoauth2sso.domain.User;
//import com.microideal.springsecurityoauth2sso.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.security.Principal;
//
///**
// * @author microideal on 2018/9/22
// */
//@Component
//public class RefreshAuthentication {
//    @Autowired
//    private SessionRegistryImpl sessionRegistry;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public void refreshAuthentication(String userName){
//        sessionRegistry.getAllPrincipals().forEach((o -> {
//            Authentication authentication = (Authentication) o;
//            if(authentication.getName().equals(userName)) {
//                User user = userRepository.findByUsername(userName);
//            }
//        }));
//    }
//
//}
