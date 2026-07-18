package com.quantummicrogrid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wind_turbine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WindTurbine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double ratedPowerKw;
    private Double cutInSpeedMs;    // vitesse de vent minimale
    private Double cutOutSpeedMs;   // vitesse de vent maximale
    private Double currentOutputKw;

    @ManyToOne
    @JoinColumn(name = "microgrid_id")
    private Microgrid microgrid;
}
