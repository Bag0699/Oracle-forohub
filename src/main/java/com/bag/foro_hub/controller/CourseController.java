package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateCourseRequest;
import com.bag.foro_hub.model.dto.response.CourseResponse;
import com.bag.foro_hub.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  @PostMapping
  public ResponseEntity<CourseResponse> save(
      @Valid @RequestBody CreateCourseRequest request, UriComponentsBuilder uriComponentsBuilder) {

    CourseResponse courseResponse = courseService.save(request);
    URI url =
        uriComponentsBuilder.path("/api/courses/{id}").buildAndExpand(courseResponse.id()).toUri();

    return ResponseEntity.created(url).body(courseResponse);
  }

  @GetMapping
  public ResponseEntity<List<CourseResponse>> findAll() {
    return ResponseEntity.ok(courseService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(courseService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CourseResponse> update(
      @PathVariable Long id, @Valid @RequestBody CreateCourseRequest request) {

    CourseResponse courseResponse = courseService.update(id, request);
    return ResponseEntity.ok(courseResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    courseService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
