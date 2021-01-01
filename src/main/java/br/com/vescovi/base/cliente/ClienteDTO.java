package br.com.vescovi.base.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClienteDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "nome nao pode ser vazio")
    private String nome;

    @CNPJ
    @NotEmpty(message = "cnpj deve ser informado")
    private String cnpj;

}
