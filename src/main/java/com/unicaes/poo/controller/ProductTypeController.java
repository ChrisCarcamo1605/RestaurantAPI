package com.unicaes.poo.controller;

import com.unicaes.poo.domain.products.IProductService;
import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.domain.products.dto.DtoTypeResponse;
import com.unicaes.poo.domain.products.dto.DtoUpdateType;
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
    private IProductService productService;


    @PostMapping
    public ResponseEntity<DtoTypeResponse> addProductType(@RequestBody DtoSaveType type,
                                                          UriComponentsBuilder uriBuilder) {

        var newType = productService.save(type.name());
        URI uri = uriBuilder.path("/product_type/{id}").buildAndExpand(newType.id()).toUri();
        return ResponseEntity.created(uri).body(newType);
    }

    @GetMapping
    public ResponseEntity<List<DtoTypeResponse>> findAll() {
        var productTypes = productService.findAll();
        return ResponseEntity.ok(productTypes);
    }

    @PutMapping
    public ResponseEntity<DtoTypeResponse> updateType(@RequestBody DtoUpdateType type) {
        var productTypes = productService.update(type.id(), type.name());
        return ResponseEntity.ok(productTypes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoTypeResponse> deleteType(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
