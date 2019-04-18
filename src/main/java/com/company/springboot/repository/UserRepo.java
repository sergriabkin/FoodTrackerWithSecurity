package com.company.springboot.repository;

import com.company.springboot.domain.Food;
import com.company.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}