package com.quantummicrogrid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "energy_consumer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyConsumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double averageDemandKw;
    private Boolean isCritical;      // priorité d'alimentation (hôpital, etc.)


    @ManyToOne
    @JoinColumn(name = "microgrid_id")
    private Microgrid microgrid;

}
