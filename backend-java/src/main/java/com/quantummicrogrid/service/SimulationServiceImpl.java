package com.quantummicrogrid.service;

import com.quantummicrogrid.dto.simulation.SimulationRequestDTO;
import com.quantummicrogrid.dto.simulation.SimulationResultDTO;
import com.quantummicrogrid.dto.simulation.SimulationStateDTO;
import com.quantummicrogrid.exception.MicrogridNotFoundException;
import com.quantummicrogrid.model.Battery;
import com.quantummicrogrid.model.Microgrid;
import com.quantummicrogrid.model.WindTurbine;
import com.quantummicrogrid.repository.MicrogridRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SimulationServiceImpl implements SimulationService {

    private final MicrogridRepository microgridRepository;

    @Override
    public SimulationResultDTO simulate(SimulationRequestDTO request) {
        Microgrid microgrid = microgridRepository.findById(request.microgridId())
                .orElseThrow(() -> new MicrogridNotFoundException(request.microgridId()));

        List<SimulationStateDTO> timeline = new ArrayList<>();
        double batteryCharge = microgrid.getBatteries().stream()
                .mapToDouble(Battery::getCurrentChargeKwh).sum();

        for (int hour = 0; hour < request.horizonHours(); hour++) {
            // Modèle simplifié — à remplacer par un vrai modèle météo/prévision
            double solar = estimateSolarOutput(microgrid, hour);
            double wind = estimateWindOutput(microgrid, hour);
            double demand = request.demandForecastKw() != null ? request.demandForecastKw() : 0;
            double deficit = Math.max(0, demand - solar - wind);

            timeline.add(new SimulationStateDTO(hour, solar, wind, batteryCharge, demand, deficit));
        }

        double totalCost = timeline.stream().mapToDouble(s -> s.gridImportKw() * 0.15).sum();
        double renewableShare = timeline.stream()
                .mapToDouble(s -> (s.solarOutputKw() + s.windOutputKw()) / Math.max(1, s.demandKw()))
                .average().orElse(0);

        return new SimulationResultDTO(request.microgridId(), timeline, totalCost, renewableShare);
    }

    private double estimateSolarOutput(Microgrid microgrid, int hour) {
        return microgrid.getSolarPanels().stream()
                .mapToDouble(sp -> sp.getPeakPowerKw() * sp.getEfficiency())
                .sum();
    }

    private double estimateWindOutput(Microgrid microgrid, int hour) {
        return microgrid.getWindTurbines().stream()
                .mapToDouble(WindTurbine::getRatedPowerKw)
                .sum() * 0.3; // facteur de charge moyen simplifié
    }
}
