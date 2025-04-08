package com.unicaes.poo.domain.bill.dto;

import com.unicaes.poo.domain.bill.BillDetails;

import java.math.BigDecimal;

    public record DtoBillDetailsList(
        Long id,
        Long billId,
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal total
) {
    public static DtoBillDetailsList fromEntity(BillDetails details) {
        return new DtoBillDetailsList(
                details.getId(),
                details.getBill().getId(),
                details.getProduct().getId(),
                details.getProduct().getName(),
                details.getQuantity(),
                details.getUnitPrice(),
                details.getTotal()
        );
    }
}