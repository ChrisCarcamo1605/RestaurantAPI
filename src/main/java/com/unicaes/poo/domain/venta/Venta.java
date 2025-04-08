package com.unicaes.poo.domain.venta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Venta")
@Table(name = "Venta")
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")

    private long idVenta;

    @Column(name = "bill_id")
    private long idTicket;

    private boolean active = true;
}
