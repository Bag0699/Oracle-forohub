package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateReplyRequest(
    @Schema(
            description = "Mensaje de respuesta para el tópico",
            example =
                "Tienes que añadir la anotación @Valid en los métodos del controlador también")
        @NotEmpty(message = "The field message cannot be blank or null")
        String message,
    @Schema(description = "Id del tópico", example = "2")
        @NotNull(message = "The field topicId cannot be null")
        Long topicId) {}