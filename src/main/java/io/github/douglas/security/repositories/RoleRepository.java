package io.github.douglas.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.douglas.security.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
