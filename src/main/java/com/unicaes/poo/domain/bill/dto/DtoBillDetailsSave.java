package com.unicaes.poo.domain.bill.dto;

public record DtoBillDetailsSave(
        Long billId,
        Long productId,
        Integer quantity
) {}