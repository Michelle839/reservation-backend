package com.michelle.reservation.service;

import com.michelle.reservation.dto.ReservationRequest;
import com.michelle.reservation.dto.ReservationResponse;
import com.michelle.reservation.entity.Reservation;
import com.michelle.reservation.entity.ReservationStatus;
import com.michelle.reservation.exception.BusinessRuleException;
import com.michelle.reservation.mapper.ReservationMapper;
import com.michelle.reservation.repository.ReservationRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
		this.reservationRepository = reservationRepository;
		this.reservationMapper = reservationMapper;
	}

	/**
	 * Returns all reservations.
	 *
	 * @return list of all reservations as response DTOs
	 */
	@Transactional(readOnly = true)
	public List<ReservationResponse> findAll() {
		return reservationRepository.findAll().stream()
				.map(reservationMapper::toResponse)
				.toList();
	}

	/**
	 * Creates a reservation only if there is no other active reservation at the same date and time.
	 *
	 * @param request the reservation data (customerName, date, time, service)
	 * @return the saved reservation as response DTO
	 * @throws BusinessRuleException if an active reservation already exists for that date and time
	 */
	@Transactional
	public ReservationResponse create(ReservationRequest request) {
		if (reservationRepository.existsByDateAndTimeAndStatus(
				request.getDate(),
				request.getTime(),
				ReservationStatus.ACTIVE)) {
			throw new BusinessRuleException(
					"An active reservation already exists for date " + request.getDate() + " and time " + request.getTime());
		}
		Reservation reservation = reservationMapper.toEntity(request);
		reservation.setStatus(ReservationStatus.ACTIVE);
		Reservation saved = reservationRepository.save(reservation);
		return reservationMapper.toResponse(saved);
	}

	/**
	 * Cancels a reservation by its id.
	 *
	 * @param id the reservation identifier
	 * @return the cancelled reservation as response DTO
	 * @throws BusinessRuleException if the reservation does not exist or is already cancelled
	 */
	@Transactional
	public ReservationResponse cancel(Long id) {
		Reservation reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new BusinessRuleException("No reservation found with id " + id));

		if (reservation.getStatus() == ReservationStatus.CANCELLED) {
			throw new BusinessRuleException("Reservation with id " + id + " is already cancelled");
		}

		reservation.setStatus(ReservationStatus.CANCELLED);
		Reservation saved = reservationRepository.save(reservation);
		return reservationMapper.toResponse(saved);
	}
}
