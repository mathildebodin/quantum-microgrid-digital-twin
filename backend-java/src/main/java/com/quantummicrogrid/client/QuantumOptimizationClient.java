package com.quantummicrogrid.client;

import com.quantummicrogrid.dto.simulation.OptimizationRequestDTO;
import com.quantummicrogrid.dto.simulation.OptimizationResultDTO;
import com.quantummicrogrid.exception.QuantumServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuantumOptimizationClient {

    private final WebClient quantumWebClient;

    public OptimizationResultDTO runOptimization(OptimizationRequestDTO request) {
        try {
            return quantumWebClient.post()
                    .uri("/api/quantum/optimize")
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            Mono.error(new QuantumServiceException(
                                    "Le service d'optimisation quantique a retourné une erreur")))
                    .bodyToMono(OptimizationResultDTO.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();
        } catch (WebClientRequestException e) {
            log.error("Impossible de contacter le service Qiskit", e);
            throw new QuantumServiceException("Service d'optimisation quantique indisponible");
        }
    }
}
