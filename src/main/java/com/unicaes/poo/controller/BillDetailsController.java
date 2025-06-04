package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.bill.BillService;
import com.unicaes.poo.domain.bill.dto.*;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bill-details")
@RequiredArgsConstructor
public class BillDetailsController {
    @Autowired
    private BillService billService;

    @GetMapping("/active")
    public ResponseEntity<?> getAllActive() {
        List<DtoBillDetailsList> activeBills = billService.getAllActive();
        return ResponseEntity.ok(MessageResponse.<List<DtoBillDetailsList>>builder()
                .message("Detalles de facturas activas recuperados exitosamente")
                .data(activeBills)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        DtoBillDetailsResponse billDetail = billService.getById(id);
        if (billDetail == null) {
            return ResponseEntity.status(404).body(MessageResponse.<DtoBillDetailsResponse>builder()
                    .message("Detalle de factura no encontrado con ID: " + id)
                    .data(null)
                    .build());
        }
        return ResponseEntity.ok(MessageResponse.<DtoBillDetailsResponse>builder()
                .message("Detalle de factura recuperado exitosamente")
                .data(billDetail)
                .build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody DtoBillDetailsSave dto,
                                    UriComponentsBuilder uriBuilder) {
        DtoBillDetailsResponse createdBillDetail = billService.save(dto);
        URI uri = uriBuilder.path("/bill-details/{id}").buildAndExpand(createdBillDetail.id()).toUri();
        return ResponseEntity.created(uri).body(MessageResponse.<DtoBillDetailsResponse>builder()
                .message("Detalle de factura creado exitosamente")
                .data(createdBillDetail)
                .build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody DtoBillDetailsUpdate dto) {
        DtoBillDetailsResponse updatedBillDetail = billService.update(dto.withId(id));
        if (updatedBillDetail == null) {
            return ResponseEntity.status(404).body(MessageResponse.<DtoBillDetailsResponse>builder()
                    .message("Detalle de factura no encontrado para actualizar con ID: " + id)
                    .data(null)
                    .build());
        }
        return ResponseEntity.accepted().body(MessageResponse.<DtoBillDetailsResponse>builder()
                .message("Detalle de factura actualizado exitosamente")
                .data(updatedBillDetail)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deactivate(@PathVariable Long id) {
        billService.deactivate(id);

        return ResponseEntity.noContent().build();
    }
}