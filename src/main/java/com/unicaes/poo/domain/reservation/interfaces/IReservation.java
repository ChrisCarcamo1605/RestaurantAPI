package com.unicaes.poo.domain.reservation.interfaces;

import com.unicaes.poo.domain.reservation.dto.DtoSaveReservation;
import com.unicaes.poo.domain.reservation.dto.DtoUpdateReservation;
import com.unicaes.poo.domain.reservation.dto.DtoReservationResponse;

import java.util.List;

public interface IReservation {

    DtoReservationResponse saveReservation(DtoSaveReservation dtoSaveReservation);

    List<DtoReservationResponse> getAllReservations();

    DtoReservationResponse getReservationById(Long id);

    DtoReservationResponse updateReservation(Long id, DtoUpdateReservation dtoUpdateReservation);

    void deleteReservation(Long id);
}
