package com.quantummicrogrid.dto.simulation;

public record EnergySourceInputDTO(
        Long id,
        String type,          // "solar" | "wind" | "battery" | "grid"
        Double capacityKw,
        Double costPerKwh
) {}
