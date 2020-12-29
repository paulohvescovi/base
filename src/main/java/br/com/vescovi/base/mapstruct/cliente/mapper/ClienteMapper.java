package br.com.vescovi.base.mapstruct.cliente.mapper;

import br.com.vescovi.base.mapstruct.cliente.Cliente;
import br.com.vescovi.base.mapstruct.cliente.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDTO dto);
    ClienteDTO toDTO(Cliente entity);

}
