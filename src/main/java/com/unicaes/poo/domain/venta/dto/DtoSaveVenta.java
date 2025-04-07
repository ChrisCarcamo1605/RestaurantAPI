package com.unicaes.poo.domain.venta.dto;

import java.math.BigDecimal;

public class DtoSaveVenta {

    private String cliente;
    private BigDecimal total;

    public DtoSaveVenta() {
    }

    public DtoSaveVenta(String cliente, BigDecimal total) {
        this.cliente = cliente;
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
