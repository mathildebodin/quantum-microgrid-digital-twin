package com.quantummicrogrid.repository;

import com.quantummicrogrid.model.Battery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryRepository extends JpaRepository<Battery, Long> {

    List<Battery> findByMicrogridId(Long microgridId);

}
