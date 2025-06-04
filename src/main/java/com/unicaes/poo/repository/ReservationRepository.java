package com.unicaes.poo.repository;

import com.unicaes.poo.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

   List<Reservation> findByActiveTrue();

}
