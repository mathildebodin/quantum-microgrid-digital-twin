package com.quantummicrogrid.service;

import com.quantummicrogrid.dto.request.MicrogridRequestDTO;
import com.quantummicrogrid.dto.response.MicrogridResponseDTO;


import java.util.List;

public interface MicrogridService {
    MicrogridResponseDTO create(MicrogridRequestDTO request);
    MicrogridResponseDTO getById(Long id);
    List<MicrogridResponseDTO> getAll();
    void delete(Long id);
}

