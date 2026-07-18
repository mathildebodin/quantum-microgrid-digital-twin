package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.MicrogridRequestDTO;
import com.quantummicrogrid.dto.response.MicrogridResponseDTO;
import com.quantummicrogrid.model.Microgrid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MicrogridMapper {

    Microgrid toEntity(MicrogridRequestDTO dto);

    MicrogridResponseDTO toResponseDto(Microgrid microgrid);

    List<Microgrid> toResponseDtos(List<Microgrid> microgrids);

}
