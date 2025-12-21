package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(
    @NotEmpty(message = "The field firstname cannot be blank or null") String firstName,
    @NotEmpty(message = "The field firstname cannot be blank or null") String lastName,
    @Size(max = 255, message = "Bio cannot exceed 255 characters") String bio) {}
