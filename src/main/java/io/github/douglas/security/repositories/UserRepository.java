package io.github.douglas.security.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.douglas.security.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {
}
