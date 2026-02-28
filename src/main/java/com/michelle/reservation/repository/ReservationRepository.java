package com.michelle.reservation.repository;

import com.michelle.reservation.entity.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	boolean existsByDateAndTime(LocalDate date, LocalTime time);
}

