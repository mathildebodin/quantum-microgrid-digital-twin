package com.quantummicrogrid.controller;


import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.quantummicrogrid.model.Microgrid;
import com.quantummicrogrid.repository.MicrogridRepository;


@RestController
@RequestMapping("/api/microgrids")
public class MicrogridController {


    private final MicrogridRepository repository;


    public MicrogridController(MicrogridRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Microgrid> getAll(){
        return repository.findAll();
    }

}