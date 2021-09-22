package com.example.demospringsecurity.controllers;

import com.example.demospringsecurity.domain.Role;
import com.example.demospringsecurity.domain.User;
import com.example.demospringsecurity.repo.RoleRepo;
import com.example.demospringsecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    public UserRepo userRepo;

    @Autowired
    public RoleRepo roleRepo;

    @GetMapping(path = "/profile", produces = "application/json")
    public String profile() {
        return "profile";
    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("")
    public String home() {
        return "home";
    }

    @GetMapping("/my-login")
    public String login() {
        return "login";
    }

    @GetMapping("/my-logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //thằng đã đăng nhập
            (new SecurityContextLogoutHandler()).logout(req, resp, auth);
        }
        //thực hiện thêm việc gì đó khác
        return "redirect:/contact";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/my-access-deny")
    public String myAccessDeny() {
        return "access-deny";
    }

    @GetMapping("/add-user")
    public String addUser() {
        User user = new User();
        user.setUsername("user");
        user.setPassword(new BCryptPasswordEncoder().encode("user"));
      /*  Role admin =  roleRepo.findById(new Long(1)).get();
        Role staff = roleRepo.findById(new Long(3)).get();*/
        Role roleUser = roleRepo.findById(new Long(2)).get();
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleUser);
       /* roles.add(admin);
        roles.add(staff);*/
        user.setRoles(roles);
        userRepo.save(user);
        return "";
    }
}
