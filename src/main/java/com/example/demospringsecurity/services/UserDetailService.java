package com.example.demospringsecurity.services;

import com.example.demospringsecurity.domain.Role;
import com.example.demospringsecurity.domain.User;
import com.example.demospringsecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        Set<Role> roles =  user.getRoles();
        Set<GrantedAuthority> grantRoles = new HashSet<>();
        for (Role role: roles
             ) {
            grantRoles.add(new SimpleGrantedAuthority(role.getName()));//ROLE_STAFF, ROLE_ADMIN
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),grantRoles);
    }
}
