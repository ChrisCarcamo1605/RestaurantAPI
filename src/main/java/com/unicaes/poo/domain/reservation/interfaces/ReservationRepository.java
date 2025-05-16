package com.unicaes.poo.domain.reservation.interfaces;

import com.unicaes.poo.domain.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

   List<Reservation> findByActiveTrue();

}
