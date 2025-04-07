package com.unicaes.poo.domain.reservas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Getter
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "mesa_id")
    private Long mesaId;

    private String cliente;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "monto_reserva")
    private BigDecimal montoReserva;

    private boolean activo = true;

    public Reserva() {}

    public Reserva(Long mesaId, String cliente, LocalDateTime fechaHora, BigDecimal montoReserva) {
        this.mesaId = mesaId;
        this.cliente = cliente;
        this.fechaHora = fechaHora;
        this.montoReserva = montoReserva;
    }
}
