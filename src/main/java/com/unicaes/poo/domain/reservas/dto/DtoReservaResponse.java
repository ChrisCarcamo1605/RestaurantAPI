package com.unicaes.poo.domain.reservas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DtoReservaResponse {

    private Long id;
    private Long mesaId;
    private String cliente;
    private LocalDateTime fechaHora;
    private BigDecimal montoReserva;

    // Constructor
    public DtoReservaResponse(Long id, Long mesaId, String cliente, LocalDateTime fechaHora, BigDecimal montoReserva) {
        this.id = id;
        this.mesaId = mesaId;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.montoReserva = montoReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getMontoReserva() {
        return montoReserva;
    }

    public void setMontoReserva(BigDecimal montoReserva) {
        this.montoReserva = montoReserva;
    }
}
