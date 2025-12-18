package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

  private final UserService userService;

  @PostMapping()
  public ResponseEntity<UserResponse> saveAdmin(
      @Valid @RequestBody CreateUserRequest request, UriComponentsBuilder uriComponentsBuilder) {

    UserResponse userResponse = userService.saveAdmin(request);

    URI url =
        uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(userResponse.id()).toUri();
    return ResponseEntity.created(url).body(userResponse);
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }
}
