package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.bill.dto.DtoBillDetailsList;
import com.unicaes.poo.domain.bill.dto.DtoBillDetailsUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Long> {

    @Query("SELECT new com.unicaes.poo.domain.bill.dto.DtoBillDetailsList(" +
            "bd.id, bd.bill.id, bd.product.id, bd.product.name, " +
            "bd.quantity, bd.unitPrice, bd.total) " +
            "FROM BillDetails bd WHERE bd.active = true")
    List<DtoBillDetailsList> findAllActiveAsDto();

    @Query("SELECT bd FROM BillDetails bd WHERE bd.id = :id AND bd.active = true")
    Optional<BillDetails> findActiveById(@Param("id") Long id);

    boolean existsByIdAndActiveIsTrue(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE BillDetails bd SET " +
            "bd.quantity = :#{#dto.quantity}, " +
            "bd.unitPrice = :#{#dto.unitPrice}, " +
            "bd.total = :#{#dto.unitPrice} * bd.quantity " +
            "WHERE bd.id = :#{#dto.id} AND bd.active = true")
    int updateDetails(@Param("dto") DtoBillDetailsUpdate dto);

    @Modifying
    @Transactional
    @Query("UPDATE BillDetails bd SET bd.active = false WHERE bd.id = :id")
    int deactivateById(@Param("id") Long id);
}