package com.quantummicrogrid.dto.response;

import java.util.List;

public record MicrogridResponseDTO(
        Long id,
        String name,
        Double maxCapacityKw,
        Double currentLoadKw,
        List<SolarPanelResponseDTO> solarPanels,
        List<WindTurbineResponseDTO> windTurbines,
        List<BatteryResponseDTO> batteries,
        List<EnergyConsumerResponseDTO> consumers
) {}