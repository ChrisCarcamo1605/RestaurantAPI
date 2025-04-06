package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.products.Product;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "bill_details")
@Entity(name = "BillDetails")
@Getter
@Setter
@EqualsAndHashCode(of = "billDetailsId")
public class BillDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "details_id")
    long billDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "Product_id")
    Product product;

    int quantity;

    BigDecimal total;

    Boolean active;

}
