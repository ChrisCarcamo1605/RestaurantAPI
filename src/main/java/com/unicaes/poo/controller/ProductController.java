package com.unicaes.poo.controller;


import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.ProductServiceImpl;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import com.unicaes.poo.interfaces.products.ProductService;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody DtoSaveProduct dto, UriComponentsBuilder uriBuilder) {

        var product = productService.save(dto);
        URI uri = uriBuilder.path("product").buildAndExpand(product.id()).toUri();

        return ResponseEntity.created(uri).body(MessageResponse.builder().message("Producto añadido correctamente").data(product).build());
    }

    @GetMapping()
    public ResponseEntity<?> getProductsList( Pageable pageable) {
        var products = productService.getProductsList(pageable);
        return ResponseEntity.ok(MessageResponse.builder().message("Productos conseguidos correctamente").data(products).build());
    }

    @PutMapping
    public ResponseEntity<?> UpdateProduct(@Valid @RequestBody DtoUpdateProduct dto, UriComponentsBuilder uriBuilder) {

        var product = productService.updateProduct(dto);

        URI uri = uriBuilder.path("product").buildAndExpand(product.id()).toUri();

        System.out.println(product.name());
        return ResponseEntity.accepted().body(
                MessageResponse.builder().message("Producto actualizado correctamente").data(product).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
