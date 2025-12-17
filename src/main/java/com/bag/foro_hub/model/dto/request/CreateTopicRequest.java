package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTopicRequest(
    @NotEmpty(message = "The field title cannot be blank or null.") String title,
    @NotEmpty(message = "The field message cannot be blank or null.") String message,
    @NotNull(message = "The field courseId cannot be null.") Long userId,
    @NotNull(message = "The field courseId cannot be null.") Long courseId) {}
