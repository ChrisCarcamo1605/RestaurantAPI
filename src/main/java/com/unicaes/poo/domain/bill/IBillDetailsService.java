package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.products.Product;

import java.util.List;

public interface IBillDetailsService {
    List<BillDetails> getAll();
    BillDetails save(Long productId, int quantity);
    void delete(Long id);
    List<BillDetails> findByProduct(Product product);
}