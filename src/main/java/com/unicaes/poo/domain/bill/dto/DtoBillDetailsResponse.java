package com.unicaes.poo.domain.bill.dto;

import com.unicaes.poo.domain.bill.BillDetails;

import java.math.BigDecimal;

public record DtoBillDetailsResponse(
        Long id,
        Long billId,
        Long productId,
        String productName,
        String productDescription,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal total,
        Boolean active
) {
    public static DtoBillDetailsResponse fromEntity(BillDetails details) {
        return new DtoBillDetailsResponse(
                details.getId(),
                details.getBill().getId(),
                details.getProduct().getId(),
                details.getProduct().getName(),
                details.getProduct().getDescription(),
                details.getQuantity(),
                details.getUnitPrice(),
                details.getTotal(),
                details.getActive()
        );
    }
}