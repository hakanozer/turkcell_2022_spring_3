package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class LoginController {

    final TinkEncDec tinkEncDec;

    @GetMapping("/")
    public String login(Model model) {
        String pass = tinkEncDec.encrypt("12345");
        System.out.println(pass);

        String newsPass = tinkEncDec.decrypt(pass);
        System.out.println(newsPass);
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
