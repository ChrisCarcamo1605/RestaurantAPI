package com.unicaes.poo.domain.sale;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Sale")
@Getter
@Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private long idSale;

    @Column(name = "bill_id")
    private long idBill;

    private boolean active = true;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "total")
    private BigDecimal total;
}
