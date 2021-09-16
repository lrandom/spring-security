package com.example.demospringsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
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
}
