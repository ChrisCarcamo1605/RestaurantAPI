package com.unicaes.poo.domain.bill;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "bills")
@Entity(name = "Bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "bill_id")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    long id;

    @Column(name = "emission_date")
    LocalDateTime emissionDate;

    String customer;

    @Column(name = "done_date")
    LocalDateTime doneDate;

    BigDecimal totalAmount;

    Boolean active;

}
