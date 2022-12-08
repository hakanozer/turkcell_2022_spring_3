package com.works.configs;

import com.works.services.AdminDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final AdminDetailService adminDetailService;
    final PasswordEncoder passwordEncoder;

    // username and password -> slq select
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailService).passwordEncoder(passwordEncoder);
    }

    // basic auth vb -> role mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/product/**").hasRole("product")
        .antMatchers("/customer/**").hasRole("customer")
        .and()
        .csrf().disable().formLogin().disable();

        http.headers().frameOptions().disable();
    }

    /*
        ali@mail.com -> product
        veli@mail.com -> customer
        zehra@mail.com -> product, customer
     */
}
