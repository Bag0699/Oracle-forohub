package com.bag.foro_hub.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProfileResponse(
    @Schema(example = "1") Long id,
    @Schema(example = "Pedro") String firstName,
    @Schema(example = "Gallese Quiroz") String lastName,
    @Schema(example = "Desarrollador en Java con 2 años de experiencia") String bio) {}
