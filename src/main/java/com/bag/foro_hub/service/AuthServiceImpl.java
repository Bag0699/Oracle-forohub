package com.bag.foro_hub.service;

import com.bag.foro_hub.mapper.AuthMapper;
import com.bag.foro_hub.model.dto.request.LoginRequest;
import com.bag.foro_hub.model.dto.response.AuthResponse;
import com.bag.foro_hub.security.CustomUserDetails;
import com.bag.foro_hub.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final JwtUtils jwtUtils;
  private final AuthMapper authMapper;
  private final AuthenticationManager authManager;

  @Override
  public AuthResponse login(LoginRequest request) {
    Authentication authentication =
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.email(), request.password()));

    if (!(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
      throw new IllegalStateException(
          "The authentication principal is not of type CustomUserDetails.");
    }

    String token = jwtUtils.generateToken(userDetails);

    return authMapper.toAuthResponse(userDetails, token, userDetails.getRoleClean());
  }
}
