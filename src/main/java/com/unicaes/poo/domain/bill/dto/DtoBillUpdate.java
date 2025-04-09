package com.unicaes.poo.domain.bill.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoBillUpdate(Long id,


                            Long waiter,
                            LocalDateTime doneDate,
                            BigDecimal totalAmount
                           ) {
}
