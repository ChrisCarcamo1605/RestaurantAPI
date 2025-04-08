package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.cliente.Cliente;
import com.unicaes.poo.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "bills")
@Entity(name = "Bill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    long id;

    @Column(name = "emission_Date")
    LocalDateTime emissionDate;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    Cliente customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiter")
    User waiter;

    @Column(name = "done_date")
    LocalDateTime doneDate;

    @Column(name = "total_amount")
    BigDecimal totalAmount;

    @Column(name = "active")
    Boolean active;

}
