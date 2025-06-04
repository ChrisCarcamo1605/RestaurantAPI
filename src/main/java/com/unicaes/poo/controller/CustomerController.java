package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.customer.ICustomerService;
import com.unicaes.poo.domain.customer.dto.*;
import com.unicaes.poo.payload.MessageResponse;
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

    @GetMapping
    public ResponseEntity<MessageResponse> findAll() {
        List<DtoCustomerList> clientes = clienteService.findAll();
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Clientes obtenidos correctamente")
                        .data(clientes)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        var cliente = clienteService.findById(id);
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Cliente encontrado")
                        .data(cliente)
                        .build()
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageResponse> save(@RequestBody DtoCustomerSave dto, UriComponentsBuilder uriBuilder) {
        var customer = clienteService.save(dto);
        URI uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.id()).toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.builder()
                        .message("Cliente creado exitosamente")
                        .data(customer)
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MessageResponse> update(@PathVariable Long id, @RequestBody DtoCustomerUpdate dto) {
        var customer = clienteService.update(id, dto);
        return ResponseEntity.accepted().body(
                MessageResponse.builder()
                        .message("Cliente actualizado exitosamente")
                        .data(customer)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Cliente eliminado correctamente")
                        .data(null)
                        .build()
        );
    }
}
