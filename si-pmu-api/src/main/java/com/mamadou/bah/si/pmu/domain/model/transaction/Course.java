package com.mamadou.bah.si.pmu.domain.model.transaction;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

public record Course(@NotBlank String name, @NotNull LocalDate date, @NotNull @Positive Long number, List<Partant> partants) {
}
