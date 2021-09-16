package com.epam.solution.ecommerce.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epam.solution.ecommerce.model.ErrorMessage;

@ControllerAdvice
public class ECommerceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorMessage> handleException(final Throwable throwable,
			final HttpServletRequest httpServletRequest) {

		final ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), throwable.getMessage(),
				httpServletRequest.getRequestURI());

		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<ErrorMessage> handleOrderException(final OrderException orderException,
			final HttpServletRequest httpServletRequest) {

		final int httpStatus = orderException.getHttpStatus() != null ? orderException.getHttpStatus().value()
				: HttpStatus.INTERNAL_SERVER_ERROR.value();

		final ErrorMessage message = new ErrorMessage(httpStatus, new Date(), orderException.getMessage(),
				httpServletRequest.getRequestURI());

		return new ResponseEntity<ErrorMessage>(message, orderException.getHttpStatus());

	}
	
	@ExceptionHandler(OfferException.class)
	public ResponseEntity<ErrorMessage> handleOfferException(final OfferException offerException,
			final HttpServletRequest httpServletRequest) {
		
		final int httpStatus = offerException.getHttpStatus() != null ? offerException.getHttpStatus().value()
				: HttpStatus.INTERNAL_SERVER_ERROR.value();

		final ErrorMessage message = new ErrorMessage(httpStatus, new Date(), offerException.getMessage(),
				httpServletRequest.getRequestURI());

		return new ResponseEntity<ErrorMessage>(message, offerException.getHttpStatus());

	}

}
