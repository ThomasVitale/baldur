package io.arconia.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;

@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.with(VaadinSecurityConfigurer.vaadin(), configurer -> 
                    configurer.loginView("/login"))
                .build();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        var user = User.withUsername("JonSnow")
                .password("{noop}password")
                .roles("USER")
                .build();
        var admin = User.withUsername("ArabellaFigg")
                .password("{noop}patronus")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
