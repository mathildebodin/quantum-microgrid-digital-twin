package com.quantummicrogrid.service;

import com.quantummicrogrid.dto.simulation.SimulationRequestDTO;
import com.quantummicrogrid.dto.simulation.SimulationResultDTO;

public interface SimulationService {
    SimulationResultDTO simulate(SimulationRequestDTO request);

}
