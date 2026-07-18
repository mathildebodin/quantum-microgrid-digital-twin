package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.EnergyConsumerRequestDTO;
import com.quantummicrogrid.dto.response.EnergyConsumerResponseDTO;
import com.quantummicrogrid.model.EnergyConsumer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnergyConsumerMapper {

    EnergyConsumer toEntity(EnergyConsumerRequestDTO dto);

    EnergyConsumerResponseDTO toResponseDto(EnergyConsumer energyConsumer);

    List<EnergyConsumer>  toResponseDtos(List<EnergyConsumer> energyConsumers);


}
