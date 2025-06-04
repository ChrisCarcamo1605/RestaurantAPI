package com.unicaes.poo.controller;

import com.unicaes.poo.domain.supplier.SupplierServiceImpl;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierSave;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
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

    private final SupplierServiceImpl supplierService;

    @Autowired
    public SupplierController(SupplierServiceImpl supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createSupplier(
            /*@Valid*/ @RequestBody DtoSupplierSave dto,
                       UriComponentsBuilder uriBuilder) {
        DtoSuppliersResponse response = supplierService.createSupplier(dto);
        URI uri = uriBuilder.path("/suppliers/{id}")
                .buildAndExpand(response.supplierId())
                .toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.<DtoSuppliersResponse>builder()
                        .message("Proveedor creado exitosamente")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllSuppliers(
            @PageableDefault(size = 5) Pageable pageable) {
        Page<DtoSupplierList> suppliers = supplierService.getSuppliersPage(pageable);
        return ResponseEntity.ok(
                MessageResponse.<Page<DtoSupplierList>>builder()
                        .message("Proveedores recuperados exitosamente")
                        .data(suppliers)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id) {
        DtoSuppliersResponse supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoSuppliersResponse>builder()
                            .message("Proveedor no encontrado con ID: " + id)
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.ok(
                MessageResponse.<DtoSuppliersResponse>builder()
                        .message("Proveedor recuperado exitosamente")
                        .data(supplier)
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateSupplier(
            @PathVariable Long id,
            /*@Valid*/ @RequestBody DtoUpdateSupplier dto) {
        DtoSuppliersResponse updatedSupplier = supplierService.updateSupplier(id, dto);
        if (updatedSupplier == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoSuppliersResponse>builder()
                            .message("Proveedor no encontrado para actualizar con ID: " + id)
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.ok(
                MessageResponse.<DtoSuppliersResponse>builder()
                        .message("Proveedor actualizado exitosamente")
                        .data(updatedSupplier)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateSupplier(@PathVariable Long id) {
        supplierService.deactivateSupplier(id);
        return ResponseEntity.noContent().build();
    }
}