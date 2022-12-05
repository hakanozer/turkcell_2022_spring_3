package com.works.controllers;

import com.works.entities.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String adminLogin(@Valid Admin admin, BindingResult bindingResult, Model model) {
        if ( bindingResult.hasErrors() ) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            model.addAttribute("errors", errors);
            return "login";
        }
        System.out.println( admin );
        return "redirect:/";
    }

}
