package br.com.richardmartins.encurtadoruol.errors;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(NotFoundException rfnException) {
		NotFoundExceptionDetalhe rfnDetails = NotFoundExceptionDetalhe.Builder
                                            .newBuilder()
                                            .timestamp(new Date().getTime())
                                            .status(HttpStatus.NOT_FOUND.value())
                                            .title("Resource not found")
                                            .detail(rfnException.getMessage())
                                            .developerMessage(rfnException.getClass().getName())
                                            .build();
        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }
}
