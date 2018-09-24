package com.microideal.springsecurityoauth2sso.repository;

import com.microideal.springsecurityoauth2sso.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);

    //Optional<User> findByUsername(String username);
}
