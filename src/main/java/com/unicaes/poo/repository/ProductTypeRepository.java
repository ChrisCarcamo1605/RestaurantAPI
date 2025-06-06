package com.unicaes.poo.repository;

import com.unicaes.poo.domain.products.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
