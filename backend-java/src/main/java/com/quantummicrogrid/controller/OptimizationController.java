package com.quantummicrogrid.controller;

import com.quantummicrogrid.dto.simulation.OptimizationRequestDTO;
import com.quantummicrogrid.dto.simulation.OptimizationResultDTO;
import com.quantummicrogrid.service.OptimizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/optimization")
@RequiredArgsConstructor
public class OptimizationController {

    private final OptimizationService optimizationService;

    @PostMapping
    public ResponseEntity<OptimizationResultDTO> optimize(@Valid @RequestBody OptimizationRequestDTO request) {
        return ResponseEntity.ok(optimizationService.optimize(request));
    }
}
