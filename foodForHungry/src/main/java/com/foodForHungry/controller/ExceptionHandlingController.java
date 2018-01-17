package com.foodForHungry.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.foodForHungry.exception.InvalidRequestException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by mashara on 5/12/17.
 */

@ControllerAdvice
public class ExceptionHandlingController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

	@ExceptionHandler({InvalidRequestException.class, ParseException.class,JsonProcessingException.class})
	public ResponseEntity<VndErrors> badRequestException(final Exception e) {
		return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
	}

	private ResponseEntity<VndErrors> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<VndErrors> allException(final IllegalArgumentException e) {
		return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
	}
}
