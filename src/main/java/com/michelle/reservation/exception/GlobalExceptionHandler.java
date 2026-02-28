package com.michelle.reservation.exception;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions and maps them to appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Maps business rule violations to 404 Not Found or 409 Conflict.
	 */
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<Map<String, String>> handleBusinessRuleException(BusinessRuleException ex) {
		String message = ex.getMessage();
		HttpStatus status = message != null && message.contains("No reservation found")
				? HttpStatus.NOT_FOUND
				: HttpStatus.CONFLICT;
		return ResponseEntity.status(status).body(Map.of("message", message != null ? message : "Business rule violated"));
	}

	/**
	 * Maps validation failures (e.g. @Valid on request body) to 400 Bad Request.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage())
				.collect(Collectors.joining("; "));
		return ResponseEntity.badRequest().body(Map.of("message", errors));
	}
}
