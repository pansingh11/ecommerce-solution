package com.epam.solution.ecommerce.exception;

import org.springframework.http.HttpStatus;

public class OfferException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	public OfferException(final String message) {
        super(message);
    }
	
	public OfferException(final String message, final HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

	public OfferException(final String message, final Throwable cause, final HttpStatus httpStatus) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
