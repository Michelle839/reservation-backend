package com.michelle.reservation.mapper;

import com.michelle.reservation.dto.ReservationRequest;
import com.michelle.reservation.dto.ReservationResponse;
import com.michelle.reservation.entity.Reservation;
import org.springframework.stereotype.Component;

/**
 * Maps between reservation DTOs and the reservation entity.
 * No business logic; only field copying and type conversion.
 */
@Component
public class ReservationMapper {

	/**
	 * Converts a create/update request DTO into an entity (id and status are not set).
	 *
	 * @param request the incoming reservation data
	 * @return a new entity with request fields; caller must set id/status if needed
	 */
	public Reservation toEntity(ReservationRequest request) {
		if (request == null) {
			return null;
		}
		Reservation entity = new Reservation();
		entity.setCustomerName(request.getCustomerName());
		entity.setDate(request.getDate());
		entity.setTime(request.getTime());
		entity.setService(request.getService());
		return entity;
	}

	/**
	 * Converts an entity to the response DTO for API responses.
	 *
	 * @param reservation the persisted reservation
	 * @return the response DTO with all fields, or null if input is null
	 */
	public ReservationResponse toResponse(Reservation reservation) {
		if (reservation == null) {
			return null;
		}
		ReservationResponse response = new ReservationResponse();
		response.setId(reservation.getId());
		response.setCustomerName(reservation.getCustomerName());
		response.setDate(reservation.getDate());
		response.setTime(reservation.getTime());
		response.setService(reservation.getService());
		response.setStatus(reservation.getStatus());
		return response;
	}
}
