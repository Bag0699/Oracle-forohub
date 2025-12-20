package com.bag.foro_hub.security;

import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.model.entity.User;
import com.bag.foro_hub.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expiration}")
  private long jwtExpirationMs;

  public String generateToken(CustomUserDetails userDetails) {
    Date now = new Date();
    Date expirationDate = new Date(now.getTime() + jwtExpirationMs);

    return Jwts.builder()
        .subject(userDetails.getId().toString())
        .claim("email", userDetails.getEmail())
        .claim("role", userDetails.getRoleClean())
        .issuedAt(now)
        .expiration(expirationDate)
        .signWith(getKey())
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      SecretKey key = getKey();
      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      log.error("Invalid JWT signature: {}", e.getMessage());
    }
    return false;
  }

  public String getSubject(String token) {
    return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public Long getAuthenticatedUser() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      throw new InsufficientAuthenticationException("There is no active session");
    }

    var principal = authentication.getPrincipal();

    if (!(principal instanceof CustomUserDetails userDetails)) {
      throw new InsufficientAuthenticationException("The security principal is not valid");
    }

    return userDetails.getId();
  }

  private SecretKey getKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
  }
}
