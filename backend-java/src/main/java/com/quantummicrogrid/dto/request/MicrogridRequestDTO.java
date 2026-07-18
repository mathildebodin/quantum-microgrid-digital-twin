package com.quantummicrogrid.dto.request;

import jakarta.validation.constraints.*;

public record MicrogridRequestDTO(
        @NotBlank String name,
        @NotNull @Positive Double maxCapacityKw
) {}
