package br.com.vescovi.base.exception.handler;

import br.com.vescovi.base.exception.ClienteBadRequestException;
import br.com.vescovi.base.exception.ClienteBadRequestExceptionDetails;
import br.com.vescovi.base.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception){

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String messages = fieldErrorList.stream().map(g -> g.getDefaultMessage()).collect(Collectors.joining(","));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad request Exception, Invalid fields")
                        .details("check fields errors")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(messages)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
