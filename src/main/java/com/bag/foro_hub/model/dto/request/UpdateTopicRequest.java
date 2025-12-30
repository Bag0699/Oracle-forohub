package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicRequest(
    @Schema(description = "Nuevo titlo para el tópico", example = "Error en mis validaciones")
        @NotEmpty(message = "The field title cannot be blank or null.")
        String title,
    @Schema(
            description = "Descripción detallada del problema",
            example = "Mis validaciones mediante la anotación @Valid no funcionan")
        @NotEmpty(message = "The field message cannot be blank or null.")
        String message,
    @Schema(description = "Id del curso que pertenece el tópico", example = "5")
        @NotNull(message = "The field courseId cannot be null.")
        Long courseId) {}
