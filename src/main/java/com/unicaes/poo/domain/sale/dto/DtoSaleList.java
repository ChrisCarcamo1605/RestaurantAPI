package com.unicaes.poo.domain.sale.dto;

import com.unicaes.poo.domain.sale.Sale;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DtoSaleList(Long idSale, Long billId, LocalDate saleDate, BigDecimal total) {

    public DtoSaleList(Sale sale) {
        this(sale.getIdSale(), sale.getIdBill(), sale.getSaleDate(), sale.getTotal());
    }
}
