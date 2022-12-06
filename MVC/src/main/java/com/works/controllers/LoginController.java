package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final AdminService adminService;
    final HttpServletRequest httpServletRequest;

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
        Admin adminDB = adminService.login(admin);
        if ( adminDB != null ) {
            httpServletRequest.getSession().setAttribute("admin", adminDB);
            return "redirect:/dashboard";
        }else {
            return "redirect:/";
        }

    }

}
