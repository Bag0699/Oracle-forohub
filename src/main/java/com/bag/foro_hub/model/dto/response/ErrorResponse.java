package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponse {
  @Schema(example = "ERR_INV_REQ")
  private String code;

  @Schema(example = "400 BAD_REQUEST")
  private HttpStatus status;

  @Schema(example = "Invalid Request")
  private String message;

  @Schema(example = "[The field title cannot be blank or null.]")
  private List<String> detailMessage;

  @Schema(example = "2025-12-21T10:15:30")
  private LocalDateTime timestamp;
}
