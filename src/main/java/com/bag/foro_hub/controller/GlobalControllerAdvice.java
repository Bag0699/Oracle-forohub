package com.bag.foro_hub.controller;

import com.bag.foro_hub.exceptions.CourseNotFoundException;
import com.bag.foro_hub.exceptions.TopicNotFoundException;
import com.bag.foro_hub.exceptions.UserNotFoundException;
import com.bag.foro_hub.model.dto.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.bag.foro_hub.utils.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(TopicNotFoundException.class)
  public ErrorResponse handleTopicNotFoundException() {
    return ErrorResponse.builder()
        .code(TOPIC_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND)
        .message(TOPIC_NOT_FOUND.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ErrorResponse handleUserNotFoundException() {
    return ErrorResponse.builder()
        .code(USER_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND)
        .message(USER_NOT_FOUND.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(CourseNotFoundException.class)
  public ErrorResponse handleCourseNotFoundException() {
    return ErrorResponse.builder()
        .code(COURSE_NOT_FOUND.getCode())
        .status(HttpStatus.NOT_FOUND)
        .message(COURSE_NOT_FOUND.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleInvalidRequestException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    return ErrorResponse.builder()
        .code(INVALID_REQUEST.getCode())
        .status(HttpStatus.BAD_REQUEST)
        .message(INVALID_REQUEST.getMessage())
        .detailMessage(
            result.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList())
        .timestamp(LocalDateTime.now())
        .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handleInternalServerError(Exception exception) {

    return ErrorResponse.builder()
        .code(GENERIC_ERROR.getCode())
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(GENERIC_ERROR.getMessage())
        .detailMessage(Collections.singletonList(exception.getMessage()))
        .timestamp(LocalDateTime.now())
        .build();
  }
}
