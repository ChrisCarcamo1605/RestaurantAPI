package com.unicaes.poo.domain.venta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Venta")
@Table(name = "ventas")
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venta_id")
    private Long id;

    private String cliente;

    @Column(name = "total")
    private BigDecimal total;

    private boolean active = true;

    public Venta() {}

    public Venta(String cliente, BigDecimal total) {
        this.cliente = cliente;
        this.total = total;
    }
}
