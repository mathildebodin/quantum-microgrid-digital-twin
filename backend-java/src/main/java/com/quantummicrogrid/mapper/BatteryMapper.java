package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.BatteryRequestDTO;
import com.quantummicrogrid.dto.response.BatteryResponseDTO;
import com.quantummicrogrid.model.Battery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BatteryMapper {

    Battery toEntity(BatteryRequestDTO dto);

    @Mapping(target = "stateOfChargePercent",
            expression = "java(battery.getCurrentChargeKwh() / battery.getCapacityKwh() * 100)")
    BatteryResponseDTO toResponseDto(Battery battery);

    List<BatteryResponseDTO> toResponseDtos(List<Battery> batteries);
}
