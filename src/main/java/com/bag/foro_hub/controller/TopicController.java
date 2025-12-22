package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.security.JwtUtils;
import com.bag.foro_hub.service.TopicService;
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
@Tag(name = "Tópicos", description = "Endpoints para la gestión de preguntas y gestiones del foro")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/topics")
public class TopicController {

  private final TopicService topicService;
  private final JwtUtils jwtUtils;

  @Operation(
      summary = "Crear un nuevo tópico",
      description = "Registrar una nueva consulta en el tópico")
  @PostMapping
  public ResponseEntity<TopicResponse> save(
      @Valid @RequestBody CreateTopicRequest request, UriComponentsBuilder uriComponentsBuilder) {

    Long authId = jwtUtils.getAuthenticatedUser();

    TopicResponse topicResponse = topicService.save(request, authId);

    URI url =
        uriComponentsBuilder.path("/api/topics/{id}").buildAndExpand(topicResponse.id()).toUri();
    return ResponseEntity.created(url).body(topicResponse);
  }

  @Operation(
      summary = "Listar todos los tópicos",
      description = "Todos los usuarios pueden listarlos")
  @GetMapping
  public ResponseEntity<List<TopicResponse>> findAll() {
    return ResponseEntity.ok(topicService.findAll());
  }

  @Operation(
      summary = "Obtener un tópico por su id",
      description = "Buscar un tópico en especifico mediante su id")
  @GetMapping("/{id}")
  public ResponseEntity<TopicResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(topicService.findById(id));
  }

  @Operation(
      summary = "Actualizar un tópico",
      description = "Solo el autor original puede editarlo")
  @PutMapping("/{id}")
  public ResponseEntity<TopicResponse> update(
      @PathVariable Long id, @Valid @RequestBody UpdateTopicRequest request) {

    Long authId = jwtUtils.getAuthenticatedUser();
    TopicResponse topicResponse = topicService.update(id, request, authId);
    return ResponseEntity.ok(topicResponse);
  }

  @Operation(
      summary = "Eliminar un tópico",
      description = "Solo el autor original puede eliminarlo")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    Long authId = jwtUtils.getAuthenticatedUser();

    topicService.deleteById(id, authId);
    return ResponseEntity.noContent().build();
  }
}
