package com.michelle.reservation.repository;

import com.michelle.reservation.entity.Reservation;
import com.michelle.reservation.entity.ReservationStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	boolean existsByDateAndTime(LocalDate date, LocalTime time);

	boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}

