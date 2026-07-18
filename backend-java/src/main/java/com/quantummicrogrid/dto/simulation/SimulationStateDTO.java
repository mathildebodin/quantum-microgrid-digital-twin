package com.quantummicrogrid.dto.simulation;

public record SimulationStateDTO(
        int hour,
        double solarOutputKw,
        double windOutputKw,
        double batteryChargeKwh,
        double demandKw,
        double gridImportKw
) {}
