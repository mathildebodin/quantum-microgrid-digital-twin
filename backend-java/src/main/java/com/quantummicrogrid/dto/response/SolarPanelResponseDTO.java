package com.quantummicrogrid.dto.response;

public record SolarPanelResponseDTO(
        Long id,
        Double peakPowerKw,
        Double efficiency,
        Double currentOutputKw
) {}