package com.unicaes.poo.repository;

import com.unicaes.poo.domain.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for supplier data operations
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}