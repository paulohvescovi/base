package br.com.vescovi.base.exception.handler;

import br.com.vescovi.base.exception.ClienteBadRequestException;
import br.com.vescovi.base.exception.ClienteBadRequestExceptionDetails;
import br.com.vescovi.base.exception.ExceptionDetails;
import br.com.vescovi.base.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }

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

    /**
     * sobrescreve o MethodArgumentNotValid do spring
     * pode ser tratado como o metodo de cima com @ExceptionHandler(ClienteBadRequestException.class) por exemplo
     * mas no caso podemos sobrescrever o comportamento padrao apenas com  override
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

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
