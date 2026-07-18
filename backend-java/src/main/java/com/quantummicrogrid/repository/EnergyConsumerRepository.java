package com.quantummicrogrid.repository;

import com.quantummicrogrid.model.EnergyConsumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyConsumerRepository extends JpaRepository<EnergyConsumer, Long> {

    List<EnergyConsumer> findByMicrogridIdAndIsCriticalTrue(Long microgridId);

}