package com.practice.auth.basic.config;

import com.practice.auth.basic.model.AppUserByH2;
import com.practice.auth.basic.repository.H2UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("h2")
public class DataLoaderConfigByH2 {
    @Bean
    public CommandLineRunner dataLoader(H2UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new AppUserByH2(null, "guest", passwordEncoder.encode("guest123"), "GUEST"));
                repository.save(new AppUserByH2(null, "customer", passwordEncoder.encode("customer123"), "USER"));
                repository.save(new AppUserByH2(null, "manager", passwordEncoder.encode("manager123"), "MANAGER"));
                repository.save(new AppUserByH2(null, "admin", passwordEncoder.encode("admin123"), "ADMIN"));
            }
        };
    }
}
