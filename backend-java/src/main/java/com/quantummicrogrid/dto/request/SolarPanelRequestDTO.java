package com.quantummicrogrid.dto.request;

import jakarta.validation.constraints.*;

public record SolarPanelRequestDTO(
        @NotNull @Positive Double peakPowerKw,
        @NotNull @DecimalMin("0.0") @DecimalMax("1.0") Double efficiency,
        @NotNull @Positive Double surfaceM2,
        Long microgridId
) {}