package com.quantummicrogrid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quantummicrogrid.model.Microgrid;


public interface MicrogridRepository extends JpaRepository<Microgrid, Long> {

}