package br.com.vescovi.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteBadRequestException extends RuntimeException {

    public ClienteBadRequestException(String message) {
        super(message);
    }
}
