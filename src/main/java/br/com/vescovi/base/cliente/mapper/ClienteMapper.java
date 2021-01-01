package br.com.vescovi.base.cliente.mapper;

import br.com.vescovi.base.cliente.Cliente;
import br.com.vescovi.base.cliente.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", implementationName = "test")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO dto);
    ClienteDTO toDTO(Cliente entity);

}
