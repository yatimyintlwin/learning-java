package com.practice.auth.basic.repository;

import com.practice.auth.basic.model.AppUserByH2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface H2UserRepository extends JpaRepository<AppUserByH2, Long> {
    Optional<AppUserByH2> findByUsername(String username);
}