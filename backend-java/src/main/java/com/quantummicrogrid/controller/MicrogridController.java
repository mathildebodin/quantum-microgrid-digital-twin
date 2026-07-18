package com.quantummicrogrid.controller;


import com.quantummicrogrid.dto.request.MicrogridRequestDTO;
import com.quantummicrogrid.dto.response.MicrogridResponseDTO;
import com.quantummicrogrid.service.MicrogridService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/microgrids")
@RequiredArgsConstructor
public class MicrogridController {

    private final MicrogridService microgridService;

    @PostMapping
    public ResponseEntity<MicrogridResponseDTO> create(@Valid @RequestBody MicrogridRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(microgridService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MicrogridResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(microgridService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<MicrogridResponseDTO>> getAll() {
        return ResponseEntity.ok(microgridService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        microgridService.delete(id);
        return ResponseEntity.noContent().build();
    }
}