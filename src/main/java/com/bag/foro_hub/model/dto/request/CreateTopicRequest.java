package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreateTopicRequest(
    @NotEmpty(message = "The field title cannot be blank or null.") String title,
    @NotEmpty(message = "The field message cannot be blank or null.") String message,
    @NotEmpty(message = "The field courseId cannot be blank or null.") Long userId,
    @NotEmpty(message = "The field courseId cannot be blank or null.") Long courseId) {}
