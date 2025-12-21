package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "The field email cannot be blank or null.")
        @Email(message = "Email must be valid")
        String email,
    @NotBlank(message = "The field password cannot be blank or null.")
        String password
) {}
