package com.quantummicrogrid.dto.simulation;

import java.util.List;

public record SimulationResultDTO(
        Long microgridId,
        List<SimulationStateDTO> timeline,
        double totalCost,
        double totalRenewableShare
) {}