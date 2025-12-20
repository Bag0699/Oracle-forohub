package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.request.UpdateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.security.JwtUtils;
import com.bag.foro_hub.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

  private final TopicService topicService;
  private final JwtUtils jwtUtils;

  @PostMapping
  public ResponseEntity<TopicResponse> save(
      @Valid @RequestBody CreateTopicRequest request, UriComponentsBuilder uriComponentsBuilder) {

    Long authId = jwtUtils.getAuthenticatedUser();

    TopicResponse topicResponse = topicService.save(request, authId);

    URI url =
        uriComponentsBuilder.path("/api/topics/{id}").buildAndExpand(topicResponse.id()).toUri();
    return ResponseEntity.created(url).body(topicResponse);
  }

  @GetMapping
  public ResponseEntity<List<TopicResponse>> findAll() {
    return ResponseEntity.ok(topicService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(topicService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicResponse> update(
      @PathVariable Long id, @Valid @RequestBody UpdateTopicRequest request) {

    Long authId = jwtUtils.getAuthenticatedUser();
    TopicResponse topicResponse = topicService.update(id, request, authId);
    return ResponseEntity.ok(topicResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    topicService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
