package io.github.douglas.security.controllers;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.github.douglas.security.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.douglas.security.dtos.UserDto;
import io.github.douglas.security.entities.Role;
import io.github.douglas.security.repositories.RoleRepository;
import io.github.douglas.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userExists = userRepository.findByUsername(userDto.username());

        if (userExists.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var password = bCryptPasswordEncoder.encode(userDto.password());

        var newUser = new User();

        newUser.setUsername(userDto.username());
        newUser.setPassword(password);
        newUser.setRoles(Set.of(basicRole.get()));

        userRepository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
