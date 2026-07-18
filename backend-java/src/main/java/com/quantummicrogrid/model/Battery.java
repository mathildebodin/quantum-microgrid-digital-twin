package com.quantummicrogrid.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "battery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double capacityKwh;
    private Double currentChargeKwh;
    private Double maxChargeRateKw;
    private Double maxDischargeRateKw;
    private Double roundTripEfficiency; // pertes charge/décharge


    @ManyToOne
    @JoinColumn(name = "microgrid_id")
    private Microgrid microgrid;
}
