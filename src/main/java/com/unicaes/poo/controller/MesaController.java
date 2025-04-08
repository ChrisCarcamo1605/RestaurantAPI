package com.unicaes.poo.controller;

import com.unicaes.poo.domain.mesa.IMesaService;
import com.unicaes.poo.domain.mesa.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final IMesaService mesaService;

    @Autowired
    public MesaController(IMesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public List<DtoMesaList> findAll() {
        return mesaService.findAll();
    }

    @GetMapping("/{id}")
    public DtoMesaResponse findById(@PathVariable Long id) {
        return mesaService.findById(id);
    }

    @PostMapping
    public DtoMesaResponse save(@RequestBody DtoMesaSave dto) {
        return mesaService.save(dto);
    }

    @PutMapping("/{id}")
    public DtoMesaResponse update(@PathVariable Long id, @RequestBody DtoMesaUpdate dto) {
        return mesaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mesaService.delete(id);
    }
}
