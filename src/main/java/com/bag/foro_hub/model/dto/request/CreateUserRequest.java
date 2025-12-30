package com.bag.foro_hub.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
    @Schema(
            description = "Correo electrónico que servirá como nombre de usuario",
            example = "example@example.com")
        @NotEmpty(message = "The field email cannot be blank or null")
        @Email(message = "Email should be valid")
        String email,
    @Schema(description = "Contraseña de acceso", example = "p@asd1!", format = "password")
        @NotEmpty(message = "The field password cannot be blank or null")
        String password,
    @Schema(description = "Primer nombre del usuario", example = "Pedro")
        @NotEmpty(message = "The field firstname cannot be blank or null")
        String firstName,
    @Schema(description = "Apellidos del usuario", example = "Gallese Quiroz")
        @NotEmpty(message = "The field firstname cannot be blank or null")
        String lastName,
    @Schema(
            description = "Breve biografía del perfil del usuario",
            example = "Desarrollador en Java con 2 años de experiencia")
        @Size(max = 255, message = "Bio cannot exceed 255 characters")
        String bio) {}
