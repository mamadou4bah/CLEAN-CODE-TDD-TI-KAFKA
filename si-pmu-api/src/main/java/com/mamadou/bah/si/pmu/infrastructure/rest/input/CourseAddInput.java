package com.mamadou.bah.si.pmu.infrastructure.rest.input;

import com.mamadou.bah.si.pmu.domain.model.transaction.Partant;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CourseAddInput(@NotBlank String name, @NotNull LocalDate date, @NotNull @Positive Long number, @NotEmpty List<@Valid Partant> partants) {
}
