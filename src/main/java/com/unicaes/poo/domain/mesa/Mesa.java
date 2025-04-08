package com.unicaes.poo.domain.mesa;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "id_mesa")
    private Long id;

    private String numero;
    private int capacidad;

    @Column ( name = "activo_mesa")
    private Boolean activo = true;


}

