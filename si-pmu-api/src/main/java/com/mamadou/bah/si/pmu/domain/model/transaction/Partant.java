package com.mamadou.bah.si.pmu.domain.model.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Partant(@NotBlank String name, @NotNull @Positive Long number) {
}
