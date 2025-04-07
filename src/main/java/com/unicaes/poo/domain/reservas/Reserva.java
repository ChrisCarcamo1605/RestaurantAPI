package com.unicaes.poo.domain.reservas;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private boolean activo;



}
