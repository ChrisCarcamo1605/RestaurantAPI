package com.unicaes.poo.controller;

import com.unicaes.poo.domain.customer.ICustomerService;
import com.unicaes.poo.domain.customer.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClienteController {


    @Autowired
    private ICustomerService clienteService;




//
//    public ClienteController(IClienteService clienteService) {
//        this.clienteService = clienteService;
//    }

    @GetMapping
    public List<DtoCustomerList> findAll() {

          return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public DtoCustomerResponse findById(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @PostMapping
    public DtoCustomerResponse save(@RequestBody DtoCustomerSave dto) {
        return clienteService.save(dto);
    }

    @PutMapping("/{id}")
    public DtoCustomerResponse update(@PathVariable Long id, @RequestBody DtoCustomerUpdate dto) {
        return clienteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clienteService.delete(id);
    }
}
