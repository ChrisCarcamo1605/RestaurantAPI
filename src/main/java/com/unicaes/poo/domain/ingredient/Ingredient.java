package com.unicaes.poo.domain.ingredient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import  com.unicaes.poo.domain.consumables.Consumable;
import  com.unicaes.poo.domain.products.Product;


@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumable_id")
    private Consumable consumable;

    @Column(nullable = false)
    private Double quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}