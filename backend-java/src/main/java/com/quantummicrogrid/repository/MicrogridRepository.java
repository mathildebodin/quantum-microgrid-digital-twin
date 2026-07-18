package com.quantummicrogrid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quantummicrogrid.model.Microgrid;

import java.util.Optional;


public interface MicrogridRepository extends JpaRepository<Microgrid, Long> {
    Optional<Microgrid> findByName(String name);

}