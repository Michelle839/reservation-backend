package com.michelle.reservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ReservationRequest {

	@NotBlank(message = "Customer name is required")
	private String customerName;

	@NotNull(message = "Date is required")
	private LocalDate date;

	@NotNull(message = "Time is required")
	private LocalTime time;

	@NotBlank(message = "Service is required")
	private String service;
}
