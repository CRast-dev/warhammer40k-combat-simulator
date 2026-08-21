package com.warhammer.dto;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CreateModelRequestDTO(
        @Min(0)
        int toughness,
        @Min(1)
        int save,
        @Min(0)
        int invulnSave,
        @Min(1)
        int maxWounds,
        @Min(0)
        int movement,
        @NotEmpty
        List<CreateWeaponRequestDTO> weapons,
        boolean isCharacter) {}
