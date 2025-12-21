package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.CreateUserRequest;
import com.bag.foro_hub.model.dto.request.LoginRequest;
import com.bag.foro_hub.model.dto.response.AuthResponse;
import com.bag.foro_hub.model.dto.response.UserResponse;
import com.bag.foro_hub.service.AuthService;
import com.bag.foro_hub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponse> register(
      @Valid @RequestBody CreateUserRequest request, UriComponentsBuilder uriComponentsBuilder) {

    UserResponse userResponse = userService.saveUser(request);

    URI url =
        uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(userResponse.id()).toUri();
    return ResponseEntity.created(url).body(userResponse);
  }
}
