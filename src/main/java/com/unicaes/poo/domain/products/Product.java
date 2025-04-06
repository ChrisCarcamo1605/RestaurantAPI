package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Product")
@Table(name = "Products")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String name;
    private String description;
    @Column(name = "price_cost")
    private BigDecimal priceCost;

    @Column(name = "price_sell")
    private BigDecimal priceSell;

    @Enumerated(EnumType.STRING)
    private MeasurementUnit measurementUnit;

    private boolean active = true;
}
