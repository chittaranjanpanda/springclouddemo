package com.demo.adminserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin().defaultSuccessUrl("/#/applications").loginPage("/login").permitAll();

        http.logout().logoutUrl("/logout");

        http.csrf().disable();


        http.authorizeRequests().antMatchers("/login", "/**/*.css", "/img/**", "/third-party/**").permitAll();

        http.authorizeRequests().antMatchers("/**").authenticated();

        http.httpBasic();
    }
}
