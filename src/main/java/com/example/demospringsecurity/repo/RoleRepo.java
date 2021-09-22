package com.example.demospringsecurity.repo;

import com.example.demospringsecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
