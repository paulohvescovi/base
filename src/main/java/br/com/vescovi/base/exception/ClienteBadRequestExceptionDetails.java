package br.com.vescovi.base.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class ClienteBadRequestExceptionDetails extends ExceptionDetails {

}
