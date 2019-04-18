package com.company.springboot.service;

import com.company.springboot.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User save(User user);

}
