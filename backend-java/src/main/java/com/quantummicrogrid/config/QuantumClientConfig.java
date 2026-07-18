package com.quantummicrogrid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class QuantumClientConfig {

    @Value("${quantum.service.url}")
    private String quantumServiceUrl;

    @Bean
    public WebClient quantumWebClient() {
        return WebClient.builder()
                .baseUrl(quantumServiceUrl)
                .build();
    }
}
