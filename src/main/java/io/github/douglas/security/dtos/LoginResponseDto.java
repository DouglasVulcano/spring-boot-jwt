package io.github.douglas.security.dtos;

public record LoginResponseDto(
        String token,
        Long expiresIn) {
}
