package com.unicaes.poo.controller;

import com.unicaes.poo.domain.sale.dto.DtoSaleResponse;
import com.unicaes.poo.domain.sale.dto.DtoSaleList;
import com.unicaes.poo.domain.sale.dto.DtoSaveSale;
import com.unicaes.poo.domain.sale.SaleServiceImpl;
import com.unicaes.poo.domain.sale.dto.DtoUpdateSale;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
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
    private SaleServiceImpl saleService;

    @PostMapping
    @Transactional
    public ResponseEntity<MessageResponse> addSale(@Valid @RequestBody DtoSaveSale dto, UriComponentsBuilder uriBuilder) {
        var sale = saleService.save(dto);
        var response = new DtoSaleResponse(sale.getIdSale(), sale.getIdBill());
        URI uri = uriBuilder.path("/sale/{id}").buildAndExpand(sale.getIdSale()).toUri();
        return ResponseEntity
                .created(uri)
                .body(MessageResponse.builder()
                        .message("Venta creada exitosamente")
                        .data(response)
                        .build());
    }

    @GetMapping
    public ResponseEntity<MessageResponse> getSalesList() {
        List<DtoSaleList> sales = saleService.getSalesList();
        return ResponseEntity.ok(MessageResponse.builder()
                .message("Listado de ventas obtenido correctamente")
                .data(sales)
                .build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MessageResponse> updateSale(@PathVariable Long id, @Valid @RequestBody DtoUpdateSale dto) {
        var sale = saleService.updateSale(id, dto);
        var response = new DtoSaleResponse(sale.getIdSale(), sale.getIdBill());
        return ResponseEntity.accepted().body(MessageResponse.builder()
                .message("Venta actualizada correctamente")
                .data(response)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("Venta eliminada correctamente")
                .data(null)
                .build());
    }
}
