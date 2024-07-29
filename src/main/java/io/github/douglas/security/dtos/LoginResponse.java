package io.github.douglas.security.dtos;

public record LoginResponse(
        String token,
        Long expiresIn) {
}
