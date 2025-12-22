package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReplyResponse(
    @Schema(example = "1") Long id,
    @Schema(
            example =
                "Tienes que añadir la anotación @Valid en los métodos del controlador también")
        String message,
    @Schema(example = "2025-12-21") String creationDate,
    @Schema(example = "true") Boolean isSolution,
    @Schema(example = "1") UserResponse user,
    @Schema(example = "1") TopicResponse topic) {}
