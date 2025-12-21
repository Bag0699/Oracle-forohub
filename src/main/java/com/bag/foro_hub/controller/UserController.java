package com.bag.foro_hub.controller;

import com.bag.foro_hub.model.dto.request.UpdateProfileRequest;
import com.bag.foro_hub.model.dto.response.ProfileResponse;

import com.bag.foro_hub.security.JwtUtils;
import com.bag.foro_hub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final JwtUtils jwtUtils;

  @GetMapping("/profile")
  public ResponseEntity<ProfileResponse> getProfile() {
    Long authId = jwtUtils.getAuthenticatedUser();

    return ResponseEntity.ok(userService.getProfile(authId));
  }

  @PutMapping("/profile")
  public ResponseEntity<ProfileResponse> updateProfile(
      @Valid @RequestBody UpdateProfileRequest request) {
    Long authId = jwtUtils.getAuthenticatedUser();

    return ResponseEntity.ok(userService.updateProfile(authId, request));
  }
}
