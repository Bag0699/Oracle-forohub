package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record CreateCourseRequest(
    @Schema(description = "Nombre único del curso", example = "NestJs")
        @NotEmpty(message = "The field name cannot be blank or null")
        String name,
    @Schema(
            description = "Categoria del curso según a la especialidad",
            example = "BACKEND",
            allowableValues = {"PROGRAMING", "FRONTED", "BACKEND", "DATA_SCIENCE"})
        @NotEmpty(message = "The field category cannot be blank or null")
        String category) {}
