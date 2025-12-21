package com.bag.foro_hub.service;

import com.bag.foro_hub.model.dto.request.LoginRequest;
import com.bag.foro_hub.model.dto.response.AuthResponse;

public interface AuthService {
  AuthResponse login(LoginRequest request);
}
