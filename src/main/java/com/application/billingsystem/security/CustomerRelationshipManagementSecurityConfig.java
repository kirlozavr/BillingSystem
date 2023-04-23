package com.application.billingsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomerRelationshipManagementSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers( "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/manager/billing/run").permitAll()
                .requestMatchers(HttpMethod.PUT, "/manager/changeTariff/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/manager/subscriber/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/abonent/payment/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/abonent/report/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .formLogin().disable()
                .httpBasic().disable();
        return http.build();
    }
}
