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

    /** Ограничение доступа для разных категорий, админ может все.
     * Я не стал добавлять авторизацию, роли и т.д. в целях удобства,
     * ограничение стоит только на сами http запросы. **/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/manager/billing/run").permitAll()
                .requestMatchers(HttpMethod.PUT, "/manager/changeTariff/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/manager/subscriber/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/abonent/payment/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/abonent/report/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .httpBasic().disable();
        return http.build();
    }
}
