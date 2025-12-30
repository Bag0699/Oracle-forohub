package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(
    @Schema(example = "eYsDaZXas1...") String token,
    @Schema(example = "1") Long id,
    @Schema(example = "example@example.com") String email,
    @Schema(example = "USER") String role) {}
