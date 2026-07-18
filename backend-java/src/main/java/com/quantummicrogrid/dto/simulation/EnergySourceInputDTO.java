package com.quantummicrogrid.dto.simulation;

// DTO interne utilisé par OptimizationRequestDTO
public record EnergySourceInputDTO(
        Long id,
        String type,          // "solar" | "wind" | "battery" | "grid"
        Double capacityKw,
        Double costPerKwh
) {}
