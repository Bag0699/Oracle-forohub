package com.bag.foro_hub.model.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record CreateCourseRequest(
    @NotEmpty(message = "The field name cannot be blank or null") String name,
    @NotEmpty(message = "The field category cannot be blank or null") String category) {}
