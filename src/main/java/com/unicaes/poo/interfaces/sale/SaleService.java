package com.unicaes.poo.interfaces.sale;

import com.unicaes.poo.domain.sale.Sale;
import com.unicaes.poo.domain.sale.dto.DtoSaveSale;

public interface SaleService {

    public Sale save(DtoSaveSale dto);
}
