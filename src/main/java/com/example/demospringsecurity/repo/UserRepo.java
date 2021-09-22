package com.example.demospringsecurity.repo;

import com.example.demospringsecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    public User findByUsername(String username);
}
