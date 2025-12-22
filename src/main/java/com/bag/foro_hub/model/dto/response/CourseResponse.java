package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CourseResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "NestJS") String name,
    @Schema(example = "BACKEND") String category) {}
