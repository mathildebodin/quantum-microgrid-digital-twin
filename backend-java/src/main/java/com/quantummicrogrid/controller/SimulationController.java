package com.quantummicrogrid.controller;

import com.quantummicrogrid.dto.simulation.SimulationRequestDTO;
import com.quantummicrogrid.dto.simulation.SimulationResultDTO;
import com.quantummicrogrid.service.SimulationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulation")
@RequiredArgsConstructor
public class SimulationController {

    private final SimulationService simulationService;

    @PostMapping
    public ResponseEntity<SimulationResultDTO> simulate(@Valid @RequestBody SimulationRequestDTO request) {
        return ResponseEntity.ok(simulationService.simulate(request));
    }
}