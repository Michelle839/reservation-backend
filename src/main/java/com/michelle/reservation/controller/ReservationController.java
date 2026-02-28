package com.michelle.reservation.controller;

import com.michelle.reservation.dto.ReservationRequest;
import com.michelle.reservation.dto.ReservationResponse;
import com.michelle.reservation.service.ReservationService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for reservation operations.
 */
@RestController
@RequestMapping("/reservas")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	/**
	 * Lists all reservations.
	 *
	 * @return 200 OK with the list of reservations
	 */
	@GetMapping
	public ResponseEntity<List<ReservationResponse>> listAll() {
		List<ReservationResponse> reservations = reservationService.findAll();
		return ResponseEntity.ok(reservations);
	}

	/**
	 * Creates a new reservation.
	 *
	 * @param request the reservation data (validated)
	 * @return 201 Created with the created reservation in the body
	 */
	@PostMapping
	public ResponseEntity<ReservationResponse> create(@Valid @RequestBody ReservationRequest request) {
		ReservationResponse created = reservationService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	/**
	 * Cancels a reservation by id.
	 *
	 * @param id the reservation identifier
	 * @return 200 OK with the cancelled reservation in the body
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ReservationResponse> cancel(@PathVariable Long id) {
		ReservationResponse cancelled = reservationService.cancel(id);
		return ResponseEntity.ok(cancelled);
	}
}
