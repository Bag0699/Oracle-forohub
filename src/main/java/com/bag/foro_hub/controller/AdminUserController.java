package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(
    name = "Administración de Usuarios",
    description = "Operaciones exclusivas para usuarios con rol ADMIN")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/admin/users")
public class AdminUserController {

  private final UserService userService;

  @Operation(
      summary = "Crear un nuevo administrador",
      description = "Solo administradores pueden crear nuevos administradores")
  @PostMapping()
  public ResponseEntity<UserResponse> saveAdmin(
      @Valid @RequestBody CreateUserRequest request, UriComponentsBuilder uriComponentsBuilder) {

    UserResponse userResponse = userService.saveAdmin(request);

    URI url =
        uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(userResponse.id()).toUri();
    return ResponseEntity.created(url).body(userResponse);
  }

  @Operation(
      summary = "Listar todos los usuarios",
      description = "Solo administradores pueden listar todos los usuarios")
  @GetMapping
  public ResponseEntity<List<UserResponse>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
}
