package com.unicaes.poo.domain.goods;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    Page<Good> findByGoodType(GoodsTypes goodType, Pageable pageable);
    List<Good> findByStockLessThanAndActiveTrue(Double threshold);
    Page<Good> findByNameContainingIgnoreCase(String name, Pageable pageable);
}