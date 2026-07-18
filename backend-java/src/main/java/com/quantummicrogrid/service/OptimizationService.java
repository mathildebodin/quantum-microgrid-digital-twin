package com.quantummicrogrid.service;

import com.quantummicrogrid.dto.simulation.OptimizationRequestDTO;
import com.quantummicrogrid.dto.simulation.OptimizationResultDTO;

public interface OptimizationService {
    OptimizationResultDTO optimize(OptimizationRequestDTO request);

}
