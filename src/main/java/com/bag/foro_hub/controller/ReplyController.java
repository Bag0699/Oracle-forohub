package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.CreateReplyRequest;
import com.bag.foro_hub.model.dto.response.ReplyResponse;
import com.bag.foro_hub.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

  private final ReplyService replyService;

  @PostMapping
  public ResponseEntity<ReplyResponse> save(
      @Valid @RequestBody CreateReplyRequest request, UriComponentsBuilder uriComponentsBuilder) {
    ReplyResponse replyResponse = replyService.save(request);

    URI url =
        uriComponentsBuilder.path("/api/reply/{id}").buildAndExpand(replyResponse.id()).toUri();
    return ResponseEntity.created(url).body(replyResponse);
  }

  @GetMapping
  public ResponseEntity<List<ReplyResponse>> findAll() {
    return ResponseEntity.ok(replyService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReplyResponse> findById(@PathVariable Long id) {
    return ResponseEntity.ok(replyService.findById(id));
  }
}
