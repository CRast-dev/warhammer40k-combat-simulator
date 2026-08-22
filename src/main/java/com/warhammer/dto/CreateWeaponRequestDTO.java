package com.warhammer.dto;

import com.warhammer.model.Weapon;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWeaponRequestDTO(
        @NotBlank
        String name,
        @Min(0)
        int flatAttacks,
        @Min(0)
        int attackDiceSide,
        @Min(0)
        int attackDiceCount,
        @Min(1)
        int skill,
        @Min(1)
        int strength,
        int ap,
        @Min(0)
        int flatDamage,
        @Min(0)
        int damageDiceSide,
        @Min(0)
        int damageDiceCount,
        @Min(0)
        int range,
        @NotNull
        Weapon.WeaponType weaponType,
        @Min(1)
        int quantity
) {}