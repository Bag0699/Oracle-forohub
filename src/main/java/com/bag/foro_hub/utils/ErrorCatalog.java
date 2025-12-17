package com.bag.foro_hub.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
  TOPIC_NOT_FOUND("ERR_TPC_NOT_FOUND", "Topic not found"),
  USER_NOT_FOUND("ERR_USR_NOT_FOUND", "User not found"),
  COURSE_NOT_FOUND("ERR_CRS_NOT_FOUND", "Course not found"),
  INVALID_REQUEST("ERR_INV_REQ", "Invalid request"),
  GENERIC_ERROR("ERR_GEN", "Generic error");

  private final String code;
  private final String message;

  ErrorCatalog(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
