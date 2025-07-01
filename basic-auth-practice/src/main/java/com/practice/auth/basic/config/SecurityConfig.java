package com.practice.auth.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/user/**").hasAnyRole("C", "M", "A")
                        .requestMatchers("/api/manager/**").hasAnyRole("M", "A")
                        .requestMatchers("/api/admin/**").hasRole("A")
                        .requestMatchers("/api/guest/**").hasRole("G")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails guest = User.withUsername("guest")
                .password(passwordEncoder().encode("guest123"))
                .roles("G")
                .build();
        UserDetails user = User.withUsername("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("C")
                .build();
        UserDetails manager = User.withUsername("manager")
                .password(passwordEncoder().encode("manager321"))
                .roles("M")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("A")
                .build();
        return new InMemoryUserDetailsManager(guest, user, manager, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
