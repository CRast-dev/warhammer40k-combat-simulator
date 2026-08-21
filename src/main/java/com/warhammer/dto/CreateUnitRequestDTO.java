package com.warhammer.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateUnitRequestDTO(
        @NotBlank
        String name,
        @NotEmpty
        List<@Valid CreateModelRequestDTO> models) {}