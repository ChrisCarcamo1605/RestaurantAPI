package com.unicaes.poo.domain.products;

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
    long id;

    private String name;
    private String description;
    @Column(name = "price_cost")
    BigDecimal priceCost;

    @Column(name = "price_sell")
    BigDecimal priceSell;

    @Enumerated(EnumType.STRING)
    com.unicaes.poo.domain.measurementUnit.MeasurementUnit measurementUnit;

    boolean active = true;
}
