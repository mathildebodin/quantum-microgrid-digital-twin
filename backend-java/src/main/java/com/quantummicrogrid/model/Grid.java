package com.quantummicrogrid.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grid")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double importPriceKwh;
    private Double exportPriceKwh;
    private Double maxImportKw;
    private Double maxExportKw;

    @OneToOne
    @JoinColumn(name = "microgrid_id")
    private Microgrid microgrid;



}