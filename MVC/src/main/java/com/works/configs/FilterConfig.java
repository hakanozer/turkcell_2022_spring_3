package com.works.configs;

import com.works.entities.Admin;
import com.works.repositories.AdminRepository;
import com.works.services.TinkEncDec;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final TinkEncDec tinkEncDec;
    final AdminRepository adminRepository;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Server UP");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getRequestURI();
        String ip = req.getLocalAddr();
        String[] urls = {"/", "/login", "/register"};
        boolean loginStatus = true;
        for (String item : urls) {
            if (url.equals(item) || url.contains("h2-console")) {
                loginStatus = false;
                break;
            }
        }
        // Cookie Login Control
        cookieControl(req, res);
        // session control
        if (loginStatus) {
            Object obj = req.getSession().getAttribute("admin");
            if (obj == null) {
                res.sendRedirect("/");
            }else {
                Admin admin = (Admin) obj;
                req.setAttribute("admin", admin);
            }
        }

        filterChain.doFilter(req,res);
    }

    private void cookieControl(HttpServletRequest req, HttpServletResponse res) {
        if ( req.getCookies() != null ) {
            Cookie[] cookies = req.getCookies();
            for(Cookie cookie : cookies) {
                if( cookie.getName().equals("admin") ) {
                    try {
                        String val = cookie.getValue();
                        String aid = tinkEncDec.decrypt(val);
                        Long lAid = Long.parseLong(aid);
                        Optional<Admin> optionalAdmin = adminRepository.findById(lAid);
                        if (optionalAdmin.isPresent()) {
                            req.getSession().setAttribute("admin", optionalAdmin.get());
                        }else {
                            throw new Exception("Cookie Null");
                        }
                    }catch (Exception ex) {
                        Cookie deleteCookie = new Cookie("admin", "");
                        deleteCookie.setMaxAge(0);
                        res.addCookie(deleteCookie);
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Server DOWN");
    }
}
