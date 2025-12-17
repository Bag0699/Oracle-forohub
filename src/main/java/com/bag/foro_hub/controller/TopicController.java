package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateTopicRequest;
import com.bag.foro_hub.model.dto.response.TopicResponse;
import com.bag.foro_hub.service.TopiService;
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

  private final TopiService topicService;

  @PostMapping
  public ResponseEntity<TopicResponse> save(
      @Valid @RequestBody CreateTopicRequest request, UriComponentsBuilder uriComponentsBuilder) {

    TopicResponse topicResponse = topicService.save(request);

    URI url =
        uriComponentsBuilder.path("/api/topics/{id}").buildAndExpand(topicResponse.id()).toUri();
    return ResponseEntity.created(url).body(topicResponse);
  }

  @GetMapping
  public List<TopicResponse> findAll() {
    return topicService.findAll();
  }
}
