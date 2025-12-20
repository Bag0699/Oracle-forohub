package com.bag.foro_hub.model.dto.response;

public record AuthResponse(String token, Long id, String email, String role) {}
