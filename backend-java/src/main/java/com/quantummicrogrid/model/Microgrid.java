package com.quantummicrogrid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "microgrids")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Microgrid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    private Double maxCapacityKw;

    private Double currentLoadKw;

    @OneToMany(mappedBy = "microgrid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SolarPanel> solarPanels = new ArrayList<>();

    @OneToMany(mappedBy = "microgrid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<WindTurbine> windTurbines = new ArrayList<>();

    @OneToMany(mappedBy = "microgrid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Battery> batteries = new ArrayList<>();

    @OneToMany(mappedBy = "microgrid", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<EnergyConsumer> consumers = new ArrayList<>();

    @OneToOne(mappedBy = "microgrid", cascade = CascadeType.ALL)
    private Grid grid;
}