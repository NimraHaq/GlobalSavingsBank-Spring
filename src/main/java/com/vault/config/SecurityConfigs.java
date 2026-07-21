package com.vault.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigs {

    //using custom table for JDBC authentication
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, is_active from users where username = ?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, concat('ROLE_', role) from users where username = ?"
        );
        return jdbcUserDetailsManager;
    }

    //giving access to certain roles for certain paths
    @Bean
    public SecurityFilterChain securityFilters(HttpSecurity http, AuthenticationSuccessHandler roleBasedAccessHandler){

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/login", "/showFormToRegisterUser", "/processFormToRegisterUser").permitAll()
                        .requestMatchers("/assets/**","/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/dashboard/**").hasRole("CUSTOMER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(roleBasedAccessHandler)   // route by role after login
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();

    }

    @Bean
    public AuthenticationSuccessHandler roleBasedSuccessHandler() {
        return new RoleBasedAccessHandler();
    }

}
