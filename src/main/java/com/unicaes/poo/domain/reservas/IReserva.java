package com.unicaes.poo.domain.reservas;

import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;

import java.util.List;

public interface IReserva {

    DtoReservaResponse saveReserva(DtoSaveReserva dtoSaveReserva);

    List<DtoReservaResponse> getAllReservas();

    DtoReservaResponse getReservaById(Long id);

    DtoReservaResponse updateReserva(Long id, DtoUpdateReserva dtoUpdateReserva);

    void deleteReserva(Long id);
}
