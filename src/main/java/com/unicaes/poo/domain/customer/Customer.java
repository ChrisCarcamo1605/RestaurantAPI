package com.unicaes.poo.domain.customer;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "id_customer")
    private Long id;
    private String name;


}
