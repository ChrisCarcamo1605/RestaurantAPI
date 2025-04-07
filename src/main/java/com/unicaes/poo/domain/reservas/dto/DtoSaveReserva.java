package com.unicaes.poo.domain.reservas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DtoSaveReserva {

    private Long mesaId;
    private String cliente;
    private LocalDateTime fechaHora;
    private BigDecimal montoReserva;

    public DtoSaveReserva(Long mesaId, String cliente, LocalDateTime fechaHora, BigDecimal montoReserva) {
        this.mesaId = mesaId;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.montoReserva = montoReserva;
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
