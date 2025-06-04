package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.products.ProductService;
import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.domain.products.dto.DtoTypeResponse;
import com.unicaes.poo.domain.products.dto.DtoUpdateType;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product_type")
public class ProductTypeController {

    @Autowired
    private ProductService productService;


    @PostMapping
    @Transactional
    public ResponseEntity<?> addProductType(@RequestBody DtoSaveType type,
                                                          UriComponentsBuilder uriBuilder) {

        var newType = productService.save(type.name());
        URI uri = uriBuilder.path("/product_type/{id}").buildAndExpand(newType.id()).toUri();
        return ResponseEntity.created(uri).body(MessageResponse.builder().message("Producto añadido").data(newType).build());
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        var productTypes = productService.findAll();
        return ResponseEntity.ok(MessageResponse.<List<DtoTypeResponse>>builder().message("Tipos de producto recuperados exitosamente").data(productTypes).build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateType(@RequestBody DtoUpdateType type) {
        var updatedProductType = productService.update(type.id(), type.name());
        return ResponseEntity.accepted().body(MessageResponse.<DtoTypeResponse>builder().message("Tipo de producto actualizado exitosamente").data(updatedProductType).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoTypeResponse> deleteType(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
