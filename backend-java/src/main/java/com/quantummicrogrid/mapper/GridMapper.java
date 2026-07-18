package com.quantummicrogrid.mapper;

import com.quantummicrogrid.dto.request.GridRequestDTO;
import com.quantummicrogrid.dto.response.GridResponseDTO;
import com.quantummicrogrid.model.Grid;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GridMapper {

    Grid toEntity(GridRequestDTO dto);

    GridResponseDTO toResponseDto(Grid grid);

    List<Grid> toResponseDtos(List<Grid> grids);

}
