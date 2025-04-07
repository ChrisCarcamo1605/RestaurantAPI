package com.unicaes.poo.domain.venta.dto;

import java.math.BigDecimal;

public class DtoVentaResponse {

    private Long id;
    private String cliente;
    private BigDecimal total;

    public DtoVentaResponse() {
    }

    public DtoVentaResponse(Long id, String cliente, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
