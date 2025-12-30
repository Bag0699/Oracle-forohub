package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record TopicResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "Error en los ciclos for") String title,
    @Schema(example = "Que diferencia existe entre un for y un foreach?") String message,
    @Schema(example = "2025-12-21") String creationDate,
    @Schema(example = "OPEN") String status,
    UserResponse user,
    CourseResponse course) {}
