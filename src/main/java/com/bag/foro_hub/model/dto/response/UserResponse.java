package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "example@example.com") String email,
    @Schema(example = "USER") String role,
    ProfileResponse profile) {}
