package com.unicaes.poo.domain.goods;

import com.unicaes.poo.domain.goods.dto.*;
import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGood {
    DtoGoodResponse createGood(DtoGoodSave dto);
    DtoGoodResponse getGoodById(Long id);
    void deactivateGood(Long id);
    DtoGoodResponse updateGood(Long id, DtoGoodUpdate dto);
    Page<DtoGoodList> getAllGoods(Pageable pageable); // Cambiado de getActiveGoods a getAllGoods
    Page<DtoGoodList> getGoodsByType(GoodsTypes type, Pageable pageable);
    DtoGoodResponse toggleGoodStatus(Long id);
    List<DtoGoodResponse> getLowStockGoods(Double threshold);
    Page<DtoGoodList> searchGoodsByName(String name, Pageable pageable);
}