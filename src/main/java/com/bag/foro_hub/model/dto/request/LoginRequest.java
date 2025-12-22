package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @Schema(
            description = "Correo electrónico del usuario registrado",
            example = "example@example.com")
        @NotBlank(message = "The field email cannot be blank or null.")
        @Email(message = "Email must be valid")
        String email,
    @Schema(description = "Contraseña del usuario", example = "p!@asd", format = "password")
        @NotBlank(message = "The field password cannot be blank or null.")
        String password) {}