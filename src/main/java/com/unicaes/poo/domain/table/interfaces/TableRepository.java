package com.unicaes.poo.domain.table.interfaces;

import com.unicaes.poo.domain.table.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TableRepository extends JpaRepository<Table, Long> {

    List<Table> findByActiveIsTrue();

}
