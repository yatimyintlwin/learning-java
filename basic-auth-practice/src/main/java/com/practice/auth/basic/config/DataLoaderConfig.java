package com.practice.auth.basic.config;

import com.practice.auth.basic.model.AppUser;
import com.practice.auth.basic.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DataLoaderConfig {
    @Bean
    public CommandLineRunner dataLoader(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new AppUser(null, "guest", passwordEncoder.encode("guest123"), "GUEST"));
                repository.save(new AppUser(null, "customer", passwordEncoder.encode("customer123"), "USER"));
                repository.save(new AppUser(null, "manager", passwordEncoder.encode("manager321"), "MANAGER"));
                repository.save(new AppUser(null, "admin", passwordEncoder.encode("admin123"), "ADMIN"));
            }
        };
    }
}
