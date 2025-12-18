package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
    @NotEmpty(message = "The field email cannot be blank or null")
        @Email(message = "Email should be valid")
        String email,
    @NotEmpty(message = "The field password cannot be blank or null") String password,
    @NotEmpty(message = "The field firstname cannot be blank or null") String firstName,
    @NotEmpty(message = "The field firstname cannot be blank or null") String lastName,
    @Size(max = 255, message = "Bio cannot exceed 255 characters") String bio) {}
