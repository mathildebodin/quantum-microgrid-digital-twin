package com.quantummicrogrid.repository;

import com.quantummicrogrid.model.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolarPanelRepository extends JpaRepository<SolarPanel,Long> {

    List<SolarPanel> findByMicrogridId(Long microgridId);

}
