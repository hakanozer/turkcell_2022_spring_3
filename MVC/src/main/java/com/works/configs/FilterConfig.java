package com.works.configs;

import com.works.entities.Admin;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {

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

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Server DOWN");
    }
}
