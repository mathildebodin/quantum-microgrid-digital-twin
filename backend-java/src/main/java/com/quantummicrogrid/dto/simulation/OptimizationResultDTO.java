package com.quantummicrogrid.dto.simulation;

import java.util.List;

public record OptimizationResultDTO(
        Long microgridId,
        Double totalCost,
        List<SourceAllocationDTO> allocations,
        Double executionTimeMs,
        String solverUsed        // "QAOA" | "classical_fallback"
) {}

