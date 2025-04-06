package com.unicaes.poo.domain.consumables;

import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "consumables")
@Getter
@Setter
public class Consumable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consumable_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Double stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    @Column(name = "consumable_type", nullable = false, length = 20)  // Cambiado a singular
    private ConsumableTypes consumableTypes;  // Cambiado a singular

    @Enumerated(EnumType.STRING)
    @Column(name = "measurement_unit", nullable = false, length = 10)
    private MeasurementUnit measurementUnit;

    @Column(nullable = false)
    private boolean active = true;
}