package com.swp391.evproject.service;


import com.swp391.evproject.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    public com.swp391.evproject.entity.User findByUsername(String username);


    public void save(User user);

}
