package com.unicaes.poo.repository;

import com.unicaes.poo.domain.table.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findByActiveIsTrue();

}
