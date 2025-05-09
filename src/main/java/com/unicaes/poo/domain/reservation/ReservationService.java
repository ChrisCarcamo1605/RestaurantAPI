package com.unicaes.poo.domain.reservation;

import com.unicaes.poo.domain.reservation.dto.DtoSaveReservation;
import com.unicaes.poo.domain.reservation.dto.DtoUpdateReservation;
import com.unicaes.poo.domain.reservation.dto.DtoReservationResponse;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IReservation {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public DtoReservationResponse saveReservation(DtoSaveReservation dtoSaveReservation) {
        try {
            Reservation reservation = new Reservation();
            reservation.setTableId(dtoSaveReservation.tableId());
            reservation.setClient(dtoSaveReservation.client());
            reservation.setDateTime(dtoSaveReservation.dateTime());
            reservation.setReservationAmount(dtoSaveReservation.reservationAmount());
            reservation.setActive(true);

            return mapToDto(reservationRepository.save(reservation));
} catch (Exception e) {
        throw new QueryException(e.getMessage());
        }
        }

    @Override
    public List<DtoReservationResponse> getAllReservations() {
        try {
            return reservationRepository.findByActiveTrue()
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public DtoReservationResponse getReservationById(Long id) {
        try {
            var reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reservation not found"));

            return mapToDto(reservation);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public DtoReservationResponse updateReservation(Long id, DtoUpdateReservation dtoUpdateReservation) {
        try {
            var reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reservation not found"));

            if (dtoUpdateReservation.tableId() != null) {
                reservation.setTableId(dtoUpdateReservation.tableId());
            }

            if (dtoUpdateReservation.client() != null && !dtoUpdateReservation.client().isBlank()) {
                reservation.setClient(dtoUpdateReservation.client());
            }

            if (dtoUpdateReservation.dateTime() != null) {
                reservation.setDateTime(dtoUpdateReservation.dateTime());
            }

            if (dtoUpdateReservation.reservationAmount() != null) {
                reservation.setReservationAmount(dtoUpdateReservation.reservationAmount());
            }

            reservation.setActive(true);
            return mapToDto(reservationRepository.save(reservation));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public void deleteReservation(Long id) {
        try {
            var reservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Reservation not found"));

            reservation.setActive(false);
            reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    private DtoReservationResponse mapToDto(Reservation reservation) {
        return new DtoReservationResponse(
                reservation.getId(),
                reservation.getTableId(),
                reservation.getClient(),
                reservation.getDateTime(),
                reservation.getReservationAmount(),
                reservation.isActive()
        );
    }
}