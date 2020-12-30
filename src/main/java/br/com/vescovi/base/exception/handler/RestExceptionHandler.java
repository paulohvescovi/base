package br.com.vescovi.base.exception.handler;

import br.com.vescovi.base.exception.ClienteBadRequestException;
import br.com.vescovi.base.exception.ClienteBadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ClienteBadRequestException.class)
    public ResponseEntity<ClienteBadRequestExceptionDetails> handlerBadRequestExcepion(ClienteBadRequestException exception){
        return new ResponseEntity<>(
                ClienteBadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad request Exception, Check the Docmentation")
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
