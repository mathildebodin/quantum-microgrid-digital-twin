package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.WindTurbineRequestDTO;
import com.quantummicrogrid.dto.response.WindTurbineResponseDTO;
import com.quantummicrogrid.model.WindTurbine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WindTurbineMapper {
    WindTurbine toEntity(WindTurbineRequestDTO dto);

    WindTurbineResponseDTO toResponseDto(WindTurbine windTurbine);

    List<WindTurbine> toResponseDtos(List<WindTurbine> windTurbines);

}
