package com.quantummicrogrid.repository;

import com.quantummicrogrid.model.WindTurbine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WindTurbineRepository extends JpaRepository<WindTurbine,Long> {

    List<WindTurbine> findByMicrogridId(Long microgridId);

}
