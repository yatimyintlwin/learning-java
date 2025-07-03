package com.practice.auth.basic.config;

import com.practice.auth.basic.model.AppUserByDynamoDB;
import com.practice.auth.basic.repository.DynamoDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Optional;

@Configuration
@Profile("dynamodb")
@RequiredArgsConstructor
public class DataLoaderConfigByDynamoDB {
    private final DynamoDBRepository dynamoDBRepository;
    private final DynamoDbEnhancedClient enhancedClient;

    @Bean
    public CommandLineRunner dataLoader(PasswordEncoder passwordEncoder) {
        return args -> {
            DynamoDbTable<AppUserByDynamoDB> table = enhancedClient.table("AppUser", TableSchema.fromBean(AppUserByDynamoDB.class));
            try {
                table.createTable();
                System.out.println("Created DynamoDB table: AppUser");
            } catch (Exception e) {
                System.out.println("DynamoDB table already exists or cannot be created: " + e.getMessage());
            }

            Optional<AppUserByDynamoDB> existing = dynamoDBRepository.findByUsername("admin");
            if (existing.isEmpty()) {
                dynamoDBRepository.save(new AppUserByDynamoDB("guest", passwordEncoder.encode("guest321"), "GUEST"));
                dynamoDBRepository.save(new AppUserByDynamoDB("customer", passwordEncoder.encode("customer321"), "USER"));
                dynamoDBRepository.save(new AppUserByDynamoDB("manager", passwordEncoder.encode("manager321"), "MANAGER"));
                dynamoDBRepository.save(new AppUserByDynamoDB("admin", passwordEncoder.encode("admin321"), "ADMIN"));
                dynamoDBRepository.save(new AppUserByDynamoDB("admin2", passwordEncoder.encode("admin4321"), "ADMIN"));
            }
        };
    }
}