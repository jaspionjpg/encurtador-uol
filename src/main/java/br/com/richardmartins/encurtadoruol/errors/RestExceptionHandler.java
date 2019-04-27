package br.com.richardmartins.encurtadoruol.errors;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(NotFoundException exception) {

		ErrorExceptionDetalhe errorDetails = new ErrorExceptionDetalhe();
		errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
		errorDetails.setDetalhe(exception.getMessage());
		errorDetails.setTitulo("Informação não encontrada");
		errorDetails.setData(new Date());

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleResourceBadRequestException(BadRequestException exception) {

		ErrorExceptionDetalhe errorDetails = new ErrorExceptionDetalhe();
		errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
		errorDetails.setDetalhe(exception.getMessage());
		errorDetails.setTitulo("Parâmetros incorretos");
		errorDetails.setData(new Date());

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ErrorExceptionDetalhe errorDetails = new ErrorExceptionDetalhe();
		errorDetails.setStatus(status.value());
		errorDetails.setDetalhe(exception.getMessage());
		errorDetails.setTitulo("Erro interno");
		errorDetails.setData(new Date());

		return new ResponseEntity<>(errorDetails, headers, status);
	}
}
