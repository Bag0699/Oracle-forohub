package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
    @Schema(description = "Nuevo nombre del usuario", example = "Pedro Alonso")
        @NotEmpty(message = "The field firstname cannot be blank or null")
        String firstName,
    @Schema(description = "Nuevos apellidos del usuario", example = "Sanchez Aliaga")
        @NotEmpty(message = "The field firstname cannot be blank or null")
        String lastName,
    @Schema(
            description = "Actualización de la biografía ",
            example = "Desarrollador de Java con 2 años de experiencia, explorando NestJS",
            maxLength = 255)
        @Size(max = 255, message = "Bio cannot exceed 255 characters")
        String bio) {}
