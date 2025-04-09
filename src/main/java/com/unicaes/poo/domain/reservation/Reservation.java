package com.unicaes.poo.domain.reservation;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    @Column(name = "table_id")
    private Long tableId;

    private String client;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "reservation_amount")
    private BigDecimal reservationAmount;

    private boolean active;
}
