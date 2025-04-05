package com.unicaes.poo.controller;

import com.unicaes.poo.domain.supplier.SupplierService;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import jakarta.validation.Valid;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<DtoSuppliersResponse> addSupplier(
            @Valid @RequestBody DtoSupplierList dto,
            UriComponentsBuilder uriBuilder) {

        DtoSuppliersResponse response = supplierService.createSupplier(dto);

        URI uri = uriBuilder.path("/supplier/{id}")
                .buildAndExpand(response.supplierId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoSupplierList>> getSuppliersList(
            @PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(supplierService.getSuppliersPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoSuppliersResponse> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DtoSuppliersResponse> toggleStatus(@PathVariable Long id,
                                                             @RequestBody DtoUpdateSupplier dto) {
        System.out.println("toggleStatus");
        return ResponseEntity.ok( supplierService.deleteSupplier(id,dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DtoSuppliersResponse> activeSupplier(@PathVariable Long id) {
        System.out.println("activeSupplier");
        return ResponseEntity.ok( supplierService.activeSupplier(id));
    }

    @DeleteMapping("/{id}/status")
    public ResponseEntity<DtoSuppliersResponse> deleteSupplier(@PathVariable Long id) {

        supplierService.deleteSupplier(id);
        return  ResponseEntity.accepted().build();
    }
}