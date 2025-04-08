package com.unicaes.poo.domain.products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Entity(name = "ProductType")
@Table(name = "product_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    long typeId;

    String name;
}
