package com.unicaes.poo.controller;

import com.unicaes.poo.domain.supplier.SupplierService;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
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
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<DtoSuppliersResponse> createSupplier(
            @Valid @RequestBody DtoSupplierList dto,
            UriComponentsBuilder uriBuilder) {
        DtoSuppliersResponse response = supplierService.createSupplier(dto);

        URI uri = uriBuilder.path("/suppliers/{id}")
                .buildAndExpand(response.supplierId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoSupplierList>> getAllSuppliers(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<DtoSupplierList> suppliers = supplierService.getSuppliersPage(pageable);
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoSuppliersResponse> getSupplierById(@PathVariable Long id) {
        DtoSuppliersResponse supplier = supplierService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DtoSuppliersResponse> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody DtoUpdateSupplier dto) {
        DtoSuppliersResponse updatedSupplier = supplierService.updateSupplier(id, dto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateSupplier(@PathVariable Long id) {
        supplierService.deactivateSupplier(id);
        return ResponseEntity.noContent().build();
    }
}