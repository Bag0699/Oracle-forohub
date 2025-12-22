package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;
import com.bag.foro_hub.security.JwtUtils;
import com.bag.foro_hub.service.ReplyService;
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
    name = "Respuestas",
    description =
        "Endpoints para la gestión de respuestas a los tópicos y selección de soluciones.")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/replies")
public class ReplyController {

  private final ReplyService replyService;
  private final JwtUtils jwtUtils;

  @Operation(
      summary = "Publicar una nueva respuesta",
      description =
          "Crear una respuesta a un tópico en especifico. Solo usuarios registrados pueden publicar respuestas")
  @PostMapping
  public ResponseEntity<ReplyResponse> save(
      @Valid @RequestBody CreateReplyRequest request, UriComponentsBuilder uriComponentsBuilder) {

    Long authId = jwtUtils.getAuthenticatedUser();
    ReplyResponse replyResponse = replyService.save(request, authId);

    URI url =
        uriComponentsBuilder.path("/api/reply/{id}").buildAndExpand(replyResponse.id()).toUri();
    return ResponseEntity.created(url).body(replyResponse);
  }

  @Operation(
      summary = "Listar todas las respuestas",
      description = "Todos los usuarios pueden listarlas")
  @GetMapping
  public ResponseEntity<List<ReplyResponse>> findAll() {
    return ResponseEntity.ok(replyService.findAll());
  }

  @Operation(
      summary = "Buscar una respuesta por su id",
      description = "Todos los usuarios pueden buscarlas")
  @GetMapping("/{id}")
  public ResponseEntity<ReplyResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(replyService.findById(id));
  }

  @Operation(
      summary = "Marcar como solución una respuesta",
      description =
          "Marca una respuesta como la solución definitiva al tópico. El tópico cambiará su estado a SOLVED.")
  @PatchMapping("/{id}/solution")
  public ResponseEntity<Void> markAsSolution(@PathVariable Long id) {
    Long authId = jwtUtils.getAuthenticatedUser();
    replyService.markAsSolution(id, authId);

    return ResponseEntity.ok().build();
  }
}
