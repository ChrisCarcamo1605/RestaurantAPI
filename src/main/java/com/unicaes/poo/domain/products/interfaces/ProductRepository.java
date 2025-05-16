package com.unicaes.poo.domain.products.interfaces;

import com.unicaes.poo.domain.ingredient.Ingredient;
import com.unicaes.poo.domain.products.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue(Pageable pageable);



    @Query("""
select p from Product p where p.active=true and p.priceSell < :price
""")
    List<Product> findPriceLess(@Param("price") double price);
}
