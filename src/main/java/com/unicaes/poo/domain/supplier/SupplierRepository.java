package com.unicaes.poo.domain.supplier;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for supplier data operations
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}