package com.michelle.reservation.exception;

/**
 * Exception thrown when business rules of the reservation system are violated.
 */
public class BusinessRuleException extends RuntimeException {

	public BusinessRuleException(String message) {
		super(message);
	}

	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}
}
