package com.unicaes.poo.controller;

import com.unicaes.poo.domain.table.ITableService;
import com.unicaes.poo.domain.table.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class MesaController {

    @Autowired
    private ITableService mesaService;

    @GetMapping
    public List<DtoTableList> findAll() {
        return mesaService.findAll();
    }

    @GetMapping("/{id}")
    public DtoTableResponse findById(@PathVariable Long id) {
        return mesaService.findById(id);
    }

    @PostMapping
    public DtoTableResponse save(@RequestBody DtoTableSave dto) {
        return mesaService.save(dto);
    }

    @PutMapping()
    public DtoTableResponse update(@RequestBody DtoTableUpdate dto) {
        return mesaService.update( dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mesaService.delete(id);
    }
}
