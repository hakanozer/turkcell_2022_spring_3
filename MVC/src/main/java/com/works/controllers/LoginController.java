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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    final AdminService adminService;
    final HttpServletRequest httpServletRequest;
    final HttpServletResponse httpServletResponse;
    final TinkEncDec tinkEncDec;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String adminLogin(@Valid Admin admin,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam(defaultValue = "") String remember
    ) {
        if ( bindingResult.hasErrors() ) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            model.addAttribute("errors", errors);
            return "login";
        }
        Admin adminDB = adminService.login(admin);
        if ( adminDB != null ) {
            httpServletRequest.getSession().setAttribute("admin", adminDB);
            if (remember.equals("on")) {
                Cookie cookie = new Cookie("admin", tinkEncDec.encrypt(""+adminDB.getAid()));
                cookie.setMaxAge(60 * 60 * 3);
                httpServletResponse.addCookie(cookie);
            }
            return "redirect:/dashboard";
        }else {
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logout() {
        httpServletRequest.getSession().removeAttribute("admin");
        Cookie cookie = new Cookie("admin", "");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
        return "redirect:/";
    }

}
