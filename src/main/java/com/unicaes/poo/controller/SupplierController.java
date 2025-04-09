package com.unicaes.poo.controller;

import com.unicaes.poo.domain.supplier.SupplierService;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import com.unicaes.poo.infra.exceptions.ValidationException;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<?> createSupplier(
            @Valid @RequestBody DtoSupplierList dto,
            UriComponentsBuilder uriBuilder) {
        try {
            DtoSuppliersResponse response = supplierService.createSupplier(dto);

            URI uri = uriBuilder.path("/suppliers/{id}")
                    .buildAndExpand(response.supplierId())
                    .toUri();

            return ResponseEntity.created(uri).body(response);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error creating supplier");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSuppliers(
            @PageableDefault(size = 10) Pageable pageable) {
        try {
            Page<DtoSupplierList> suppliers = supplierService.getSuppliersPage(pageable);
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error getting suppliers");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        try {
            DtoSuppliersResponse supplier = supplierService.getSupplierById(id);
            return ResponseEntity.ok(supplier);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error getting supplier");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody DtoUpdateSupplier dto) {
        try {
            DtoSuppliersResponse updatedSupplier = supplierService.updateSupplier(id, dto);
            return ResponseEntity.ok(updatedSupplier);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error updating supplier");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateSupplier(@PathVariable Long id) {
        try {
            supplierService.deactivateSupplier(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error deactivating supplier");
        }
    }
}