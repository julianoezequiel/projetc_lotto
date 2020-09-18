package br.com.ot.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.ot.dto.ErrorResponse;
import br.com.ot.service.ServiceException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	
	@ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleException(ServiceException e) {
		ErrorResponse error = new ErrorResponse();
		error.setHttpStatus(e.getHttpStatus());
		error.setMessage(e.getLocalizedMessage());
        return new ResponseEntity<>(error,e.getHttpStatus());
    }  
	
}
