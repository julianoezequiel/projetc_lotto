package br.com.lotto.service;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2765330739986658569L;
	
	private HttpStatus httpStatus = HttpStatus.PRECONDITION_FAILED;

	public ServiceException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}
