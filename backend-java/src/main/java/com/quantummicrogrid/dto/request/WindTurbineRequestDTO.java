package com.quantummicrogrid.dto.request;

import jakarta.validation.constraints.*;

public record WindTurbineRequestDTO(
        @NotNull @Positive Double ratedPowerKw,
        @NotNull Double cutInSpeedMs,
        @NotNull Double cutOutSpeedMs,
        Long microgridId
) {}