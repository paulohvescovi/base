package br.com.vescovi.base.util;

import br.com.vescovi.base.mapstruct.cliente.Cliente;

public class ClienteCreator {

    public static Cliente createClienteParaSalvar(){
        return Cliente.builder()
                .nome("Cliente test jpa")
                .cnpj("64181424000182")
                .build();
    }

    public static Cliente createClienteValido(){
        return Cliente.builder()
                .nome("Cliente test jpa")
                .id(1L)
                .cnpj("64181424000182")
                .build();
    }

    public static Cliente createClienteparaUpdate(){
        return Cliente.builder()
                .nome("Cliente test jpa alterado")
                .id(1L)
                .cnpj("64181424000182")
                .build();
    }

}
