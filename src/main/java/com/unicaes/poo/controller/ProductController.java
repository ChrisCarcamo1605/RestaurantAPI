package com.unicaes.poo.controller;



import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoProductsList;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.ProductService;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<DtoProductResponse> addProduct(@Valid @RequestBody DtoSaveProduct dto, UriComponentsBuilder uriBuilder) {

        var product = productService.save(dto);

        var response = new DtoProductResponse(product.getId(), product.getName(),
                product.getPriceCost(), product.getPriceSell(), product.getDescription(), product.getMeasurementUnit());
        URI uri = uriBuilder.path("product").buildAndExpand(product.getId()).toUri();



        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoProductsList>> getProductsList(@PageableDefault(size = 3) Pageable pageable) {
        var products = productService.getProductsList(pageable);

//        for(var product: products){
//
//            System.out.println(product.name());
//        }
        return ResponseEntity.ok(products);
    }

    @PutMapping
    public  ResponseEntity<DtoProductsList>  UpdateProduct(@Valid @RequestBody DtoUpdateProduct dto, UriComponentsBuilder uriBuilder) {

        var product =  productService.updateProduct(dto);

        return ResponseEntity.ok().body(new DtoProductsList(product));
    }
}
