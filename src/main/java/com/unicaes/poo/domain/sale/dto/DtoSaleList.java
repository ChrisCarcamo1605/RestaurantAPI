package com.unicaes.poo.domain.sale.dto;

import com.unicaes.poo.domain.sale.Sale;

public record DtoSaleList(Long idSale, Long billId) {
    public DtoSaleList(Sale sale) {
        this(sale.getIdSale(), sale.getIdBill());
    }
}
