package com.quantummicrogrid.dto.simulation;

import jakarta.validation.constraints.*;

import java.util.List;

public record OptimizationRequestDTO(
        @NotNull Long microgridId,
        @NotNull @Positive Double demandKw,
        List<EnergySourceInputDTO> sources,
        @Min(1) Integer horizonHours
) {}

