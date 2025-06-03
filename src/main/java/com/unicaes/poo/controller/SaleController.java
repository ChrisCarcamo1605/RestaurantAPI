package com.unicaes.poo.controller;

import com.unicaes.poo.domain.sale.dto.DtoSaleResponse;
import com.unicaes.poo.domain.sale.dto.DtoSaleList;
import com.unicaes.poo.domain.sale.dto.DtoSaveSale;
import com.unicaes.poo.domain.sale.SaleService;
import com.unicaes.poo.domain.sale.dto.DtoUpdateSale;
import com.unicaes.poo.payload.MessageResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity<MessageResponse<DtoSaleResponse>> addSale(@Valid @RequestBody DtoSaveSale dto, UriComponentsBuilder uriBuilder) {
        var sale = saleService.save(dto);
        var response = new DtoSaleResponse(sale.getIdSale(), sale.getIdBill());
        URI uri = uriBuilder.path("/sale/{id}").buildAndExpand(sale.getIdSale()).toUri();

        return ResponseEntity.created(uri).body(
                MessageResponse.build("Venta registrada exitosamente", response)
        );
    }

    @GetMapping
    public ResponseEntity<MessageResponse<List<DtoSaleList>>> getSalesList() {
        var sales = saleService.getSalesList();

        if (sales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                MessageResponse.build("Ventas obtenidas exitosamente", sales)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<DtoSaleResponse>> updateSale(@PathVariable Long id, @Valid @RequestBody DtoUpdateSale dto) {
        var sale = saleService.updateSale(id, dto);
        var response = new DtoSaleResponse(sale.getIdSale(), sale.getIdBill());

        return ResponseEntity.accepted().body(
                MessageResponse.build("Venta actualizada exitosamente", response)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<String>> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok(
                MessageResponse.build("Venta eliminada exitosamente", null)
        );
    }
}
