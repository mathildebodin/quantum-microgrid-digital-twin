package com.quantummicrogrid.dto.response;

public record EnergyConsumerResponseDTO(
        Long id,
        String name,
        Double averageDemandKw,
        boolean isCritical
) {}