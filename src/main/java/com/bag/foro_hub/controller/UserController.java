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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping()
  public ResponseEntity<UserResponse> saveUser(
      @Valid @RequestBody CreateUserRequest request, UriComponentsBuilder uriComponentsBuilder) {

    UserResponse userResponse = userService.saveUser(request);

    URI url =
        uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(userResponse.id()).toUri();
    return ResponseEntity.created(url).body(userResponse);
  }
}
