package com.bag.foro_hub.exceptions;

public class RoleAccessDeniedException extends RuntimeException {
  public RoleAccessDeniedException(String message) {
    super(message);
  }
}
