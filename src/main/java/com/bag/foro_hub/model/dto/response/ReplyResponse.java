package com.bag.foro_hub.model.dto.response;

public record ReplyResponse(
    Long id,
    String message,
    String creationDate,
    Boolean isSolution,
    UserResponse user,
    TopicResponse topic) {}
