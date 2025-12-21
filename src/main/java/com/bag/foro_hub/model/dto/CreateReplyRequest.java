package com.bag.foro_hub.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateReplyRequest(
    @NotEmpty(message = "The field message cannot be blank or null") String message,
    @NotNull(message = "The field topicId cannot be null") Long topicId) {}
