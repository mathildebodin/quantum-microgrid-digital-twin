package com.quantummicrogrid.dto.response;

public record WindTurbineResponseDTO(
        Long id,
        Double ratedPowerKw,
        Double currentOutputKw
) {}
