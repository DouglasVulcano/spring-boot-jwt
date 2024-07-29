package io.github.douglas.security.controllers;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.douglas.security.dtos.LoginRequestDto;
import io.github.douglas.security.dtos.LoginResponseDto;
import io.github.douglas.security.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class TokenController {

    @Autowired
    private final JwtEncoder jwtEncoder;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());
        var now = Instant.now();
        Long expiresIn = 3600L;

        if (user.isEmpty() || !bCryptPasswordEncoder.matches(loginRequest.password(), user.get().getPassword())) {
            throw new BadCredentialsException("User or password is invalid!");
        }

        var claims = JwtClaimsSet.builder()
                .issuer("self")
                .subject(user.get().getId().toString())
                .expiresAt(now.plusSeconds(expiresIn))
                .issuedAt(now)
                .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDto(token, expiresIn));
    }
}
