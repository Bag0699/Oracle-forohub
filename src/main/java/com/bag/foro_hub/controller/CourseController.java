package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateCourseRequest;
import com.bag.foro_hub.model.dto.response.CourseResponse;
import com.bag.foro_hub.service.CourseService;
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
    name = "Cursos",
    description =
        "Gestión de cursos disponibles en la plataforma. Incluye operaciones administrativas.")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/")
public class CourseController {

  private final CourseService courseService;

  @Operation(
      summary = "Crear un nuevo curso",
      description = "Solo administradores pueden crear cursos")
  @PostMapping("/admin/courses")
  public ResponseEntity<CourseResponse> save(
      @Valid @RequestBody CreateCourseRequest request, UriComponentsBuilder uriComponentsBuilder) {

    CourseResponse courseResponse = courseService.save(request);
    URI url =
        uriComponentsBuilder.path("/api/courses/{id}").buildAndExpand(courseResponse.id()).toUri();

    return ResponseEntity.created(url).body(courseResponse);
  }

  @Operation(
      summary = "Listar todos los cursos",
      description = "Todos los usuarios pueden listarlos")
  @GetMapping("/courses")
  public ResponseEntity<List<CourseResponse>> findAll() {
    return ResponseEntity.ok(courseService.findAll());
  }

  @Operation(
      summary = "Buscar un curso por su id",
      description = "Todos los usuarios pueden buscarlos")
  @GetMapping("/courses/{id}")
  public ResponseEntity<CourseResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(courseService.findById(id));
  }

  @Operation(
      summary = "Actualizar un curso",
      description = "Solo administradores pueden actualizarlos")
  @PutMapping("/admin/courses/{id}")
  public ResponseEntity<CourseResponse> update(
      @PathVariable Long id, @Valid @RequestBody CreateCourseRequest request) {

    CourseResponse courseResponse = courseService.update(id, request);
    return ResponseEntity.ok(courseResponse);
  }

  @Operation(summary = "Eliminar un curso", description = "Solo administradores pueden eliminarlos")
  @DeleteMapping("/admin/courses/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    courseService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
