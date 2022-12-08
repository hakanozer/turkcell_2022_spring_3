package com.works.configs;

import com.works.entities.Info;
import com.works.repositories.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class FilterConfig implements Filter {

    final InfoRepository infoRepository;

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<String> ls = new ArrayList<>();
        for(GrantedAuthority role : auth.getAuthorities()) {
            ls.add(role.getAuthority());
        }
        String userName = auth.getName();
        String url = req.getRequestURI();
        String details = ""+auth.getDetails();
        if ( !url.contains("/h2-console") ) {
            Info i = new Info();
            i.setDate(new Date());
            i.setDetails(details);
            i.setRoles(ls.toString());
            i.setUserName(userName);
            i.setUrl(url);
            infoRepository.save(i);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
