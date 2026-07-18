package com.quantummicrogrid.service;

import com.quantummicrogrid.client.QuantumOptimizationClient;
import com.quantummicrogrid.dto.simulation.OptimizationRequestDTO;
import com.quantummicrogrid.dto.simulation.OptimizationResultDTO;
import com.quantummicrogrid.exception.MicrogridNotFoundException;
import com.quantummicrogrid.repository.MicrogridRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OptimizationServiceImpl implements OptimizationService {

    private final QuantumOptimizationClient quantumClient;
    private final MicrogridRepository microgridRepository;

    @Override
    public OptimizationResultDTO optimize(OptimizationRequestDTO request) {
        microgridRepository.findById(request.microgridId())
                .orElseThrow(() -> new MicrogridNotFoundException(request.microgridId()));

        log.info("Lancement de l'optimisation pour le microgrid {}", request.microgridId());
        return quantumClient.runOptimization(request);
    }
}
