package com.unicaes.poo.domain.table;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findByActiveIsTrue();

}
