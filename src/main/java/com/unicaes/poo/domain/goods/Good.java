package com.unicaes.poo.domain.goods;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.supplier.Supplier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "goods")
@Getter
@Setter
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false, length = 20)
    private GoodsTypes goodType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MeasurementUnit measurementUnit;

    @Column(nullable = false)
    private boolean active = true;
}