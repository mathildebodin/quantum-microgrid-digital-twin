package com.quantummicrogrid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Microgrid {

    @Id
    private Long id;

    private String name;

    private double solarCapacity;

    private double windCapacity;

    private double batteryCapacity;
}