package com.quantummicrogrid.service;

import com.quantummicrogrid.dto.request.MicrogridRequestDTO;
import com.quantummicrogrid.dto.response.MicrogridResponseDTO;
import com.quantummicrogrid.exception.MicrogridNotFoundException;
import com.quantummicrogrid.mapper.MicrogridMapper;
import com.quantummicrogrid.model.Microgrid;
import com.quantummicrogrid.repository.MicrogridRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MicrogridServiceImpl implements MicrogridService {

    private final MicrogridRepository microgridRepository;
    private final MicrogridMapper microgridMapper;

    @Override
    public MicrogridResponseDTO create(MicrogridRequestDTO request) {
        Microgrid entity = microgridMapper.toEntity(request);
        return microgridMapper.toResponseDto(microgridRepository.save(entity));
    }

    @Override
    public MicrogridResponseDTO getById(Long id) {
        return microgridRepository.findById(id)
                .map(microgridMapper::toResponseDto)
                .orElseThrow(() -> new MicrogridNotFoundException(id));
    }

    @Override
    public List<MicrogridResponseDTO> getAll() {
        return microgridRepository.findAll().stream()
                .map(microgridMapper::toResponseDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!microgridRepository.existsById(id)) {
            throw new MicrogridNotFoundException(id);
        }
        microgridRepository.deleteById(id);
    }
}
