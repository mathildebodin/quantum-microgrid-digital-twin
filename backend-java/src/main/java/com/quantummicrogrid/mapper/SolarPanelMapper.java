package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.SolarPanelRequestDTO;
import com.quantummicrogrid.dto.response.SolarPanelResponseDTO;
import com.quantummicrogrid.model.SolarPanel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SolarPanelMapper {

    SolarPanel toEntity(SolarPanelRequestDTO dto);

    SolarPanelResponseDTO toResponseDto(SolarPanel solarPanel);

    List<SolarPanel> toResponseDtos(List<SolarPanel> solarPanels);

}
