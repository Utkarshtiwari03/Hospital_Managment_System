package com.example.Hospital.Managment.System.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Hospital.Managment.System.entity.User;
import com.example.Hospital.Managment.System.entity.type.AuthProviderType;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}