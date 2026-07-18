package com.quantummicrogrid.dto.request;
import jakarta.validation.constraints.*;

public record BatteryRequestDTO(
        @NotNull @Positive Double capacityKwh,
        @NotNull @Positive Double maxChargeRateKw,
        @NotNull @Positive Double maxDischargeRateKw,
        @DecimalMin("0.0") @DecimalMax("1.0") Double roundTripEfficiency,
        Long microgridId
) {}
