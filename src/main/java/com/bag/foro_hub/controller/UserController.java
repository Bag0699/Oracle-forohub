package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.UpdateProfileRequest;
import com.bag.foro_hub.model.dto.response.ProfileResponse;

import com.bag.foro_hub.security.JwtUtils;
import com.bag.foro_hub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(
    name = "Usuario",
    description = "Operaciones relacionadas con el perfil del usuario actualmente autenticado")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final JwtUtils jwtUtils;

  @Operation(
      summary = "Obtener perfil del usuario autenticado",
      description = "Recupera la información detallada del perfil del usuario que inicio sesión")
  @GetMapping("/profile")
  public ResponseEntity<ProfileResponse> getProfile() {
    Long authId = jwtUtils.getAuthenticatedUser();

    return ResponseEntity.ok(userService.getProfile(authId));
  }

  @Operation(
      summary = "Actualizar perfil del usuario autenticado",
      description = "Permite actualizar la información del perfil del usuario que inicio sesión")
  @PutMapping("/profile")
  public ResponseEntity<ProfileResponse> updateProfile(
      @Valid @RequestBody UpdateProfileRequest request) {
    Long authId = jwtUtils.getAuthenticatedUser();

    return ResponseEntity.ok(userService.updateProfile(authId, request));
  }
}
