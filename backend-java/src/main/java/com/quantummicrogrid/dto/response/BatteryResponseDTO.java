package com.quantummicrogrid.dto.response;


public record BatteryResponseDTO(
        Long id,
        Double capacityKwh,
        Double currentChargeKwh,
        Double stateOfChargePercent
) {}
