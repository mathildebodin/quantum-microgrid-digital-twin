package com.quantummicrogrid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "solar_panels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolarPanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double peakPowerKw;
    private Double efficiency;      // 0.0 à 1.0
    private Double surfaceM2;
    private Double currentOutputKw;

    @ManyToOne
    @JoinColumn(name = "microgrid_id")
    private Microgrid microgrid;
}