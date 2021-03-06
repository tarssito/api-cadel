package br.com.apicadel.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.apicadel.services.exceptions.DataIntegrityException;
import br.com.apicadel.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<String> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<String> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());		
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
}
