package com.michelle.reservation.dto;

import com.michelle.reservation.entity.ReservationStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {

	private Long id;
	private String customerName;
	private LocalDate date;
	private LocalTime time;
	private String service;
	private ReservationStatus status;
}
