package br.com.vescovi.base.mapstruct.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClienteDTO implements Serializable {

    private Long id;
    private String nome;
    private String cnpj;

}
