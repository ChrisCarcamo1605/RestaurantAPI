package com.unicaes.poo.controller;

import com.unicaes.poo.domain.sale.dto.DtoSaleResponse;
import com.unicaes.poo.domain.sale.dto.DtoSaleList;
import com.unicaes.poo.domain.sale.dto.DtoSaveSale;
import com.unicaes.poo.domain.sale.SaleService;
import com.unicaes.poo.domain.sale.dto.DtoUpdateSale;
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
    public ResponseEntity<DtoSaleResponse> addSale(@Valid @RequestBody DtoSaveSale dto, UriComponentsBuilder uriBuilder) {
        var sale = saleService.save(dto);
        var response = new DtoSaleResponse(sale.getIdSale(), sale.getIdBill());
        URI uri = uriBuilder.path("/sale/{id}").buildAndExpand(sale.getIdSale()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DtoSaleList>> getSalesList() {
        var sales = saleService.getSalesList();
        return ResponseEntity.ok(sales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoSaleResponse> updateSale(@PathVariable Long id, @Valid @RequestBody DtoUpdateSale dto) {
        var sale = saleService.updateSale(id, dto);
        return ResponseEntity.ok().body(new DtoSaleResponse(sale.getIdSale(), sale.getIdBill()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

}
