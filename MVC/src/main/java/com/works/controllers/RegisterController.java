package com.works.controllers;

import com.works.entities.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String adminRegister(Admin admin) {
        System.out.println(admin);
        return "register";
    }

}
