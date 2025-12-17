package com.bag.foro_hub.model.dto.response;

public record TopicResponse(
        Long id,
        String title,
        String message,
        String creationDate,
        String status,
        UserResponse user,
        CourseResponse course
) {}
