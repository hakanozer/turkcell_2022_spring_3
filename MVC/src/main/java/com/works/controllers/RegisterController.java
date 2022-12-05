package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    final AdminService adminService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String adminRegister(Admin admin, Model model) {
        Admin saveAdmin = adminService.save(admin);
        if (saveAdmin == null) {
            model.addAttribute("error", "Insert Fail");
            return "register";
        }
        return "register";
    }

}
