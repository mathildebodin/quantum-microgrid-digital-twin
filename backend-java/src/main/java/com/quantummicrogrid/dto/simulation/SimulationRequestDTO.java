package com.quantummicrogrid.dto.simulation;

import jakarta.validation.constraints.*;

public record SimulationRequestDTO(
        @NotNull Long microgridId,
        @NotNull @Min(1) Integer horizonHours,
        Double demandForecastKw
) {}
