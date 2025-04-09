package com.unicaes.poo.domain.table;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "id_table")
    private Long id;


    private String number;
    private int capacity;
    private Boolean active = true;


}

