package com.unicaes.poo.repository;

import com.unicaes.poo.domain.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByActiveIsTrue();


}