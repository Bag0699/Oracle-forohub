package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTopicRequest(
    @Schema(description = "Titulo descriptivo del problema", example = "Duda en los ciclos for")
        @NotEmpty(message = "The field title cannot be blank or null.")
        String title,
    @Schema(
            description = "Descripción detallada del tópico",
            example = "Que diferencia existe entre un for y un foreach?")
        @NotEmpty(message = "The field message cannot be blank or null.")
        String message,
    @Schema(description = "El id del curso correspondiente al tópico", example = "1")
        @NotNull(message = "The field courseId cannot be null.")
        Long courseId) {}
