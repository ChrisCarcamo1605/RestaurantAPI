package com.unicaes.poo.controller;

import com.unicaes.poo.domain.customer.interfaces.ICustomerService;
import com.unicaes.poo.domain.customer.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private ICustomerService clienteService;


//
//    public ClienteController(IClienteService clienteService) {
//        this.clienteService = clienteService;
//    }

    @GetMapping
    public ResponseEntity<List<DtoCustomerList>> findAll() {

        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoCustomerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clienteService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DtoCustomerResponse> save(@RequestBody DtoCustomerSave dto, UriComponentsBuilder uriBuilder) {
        var customer = clienteService.save(dto);
        URI uri = uriBuilder.buildAndExpand(customer.id()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoCustomerResponse> update(@PathVariable Long id, @RequestBody DtoCustomerUpdate dto) {
        var customer = clienteService.update(id, dto);
        return ResponseEntity.accepted().body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
