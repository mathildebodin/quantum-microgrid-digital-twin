package com.quantummicrogrid.dto.request;

import jakarta.validation.constraints.*;


public record EnergyConsumerRequestDTO(
        @NotBlank String name,
        @NotNull @Positive Double averageDemandKw,
        boolean isCritical,
        Long microgridId
) {}
