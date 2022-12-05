package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(Model model) {
        String appTitle = "Site Title";
        String[] cities = {"Ankara", "İstanbul", "İzmir", "Adana"};
        model.addAttribute("appTitle", appTitle);
        model.addAttribute("cities", cities);
        return "login";
    }

    @PostMapping("/sendData")
    public String sendData(@RequestParam String data) {
        System.out.println(data);
        return "redirect:/";
    }

}
