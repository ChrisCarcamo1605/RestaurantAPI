package com.unicaes.poo.domain.sale;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Sale")
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
}
