package com.unicaes.poo.controller;

import com.unicaes.poo.domain.cliente.IClienteService;
import com.unicaes.poo.domain.cliente.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private  IClienteService clienteService;

//
//    public ClienteController(IClienteService clienteService) {
//        this.clienteService = clienteService;
//    }

    @GetMapping
    public List<DtoClienteList> findAll() {
        System.out.println("Messi");

        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public DtoClienteResponse findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public DtoClienteResponse save(@RequestBody DtoClienteSave dto) {
        return clienteService.save(dto);
    }

    @PutMapping("/{id}")
    public DtoClienteResponse update(@PathVariable Long id, @RequestBody DtoClienteUpdate dto) {
        return clienteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
}
