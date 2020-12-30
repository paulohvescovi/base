package br.com.vescovi.base.mapstruct.cliente;

import br.com.vescovi.base.exception.ClienteBadRequestException;
import br.com.vescovi.base.framework.controller.BaseController;
import br.com.vescovi.base.framework.service.BaseService;
import br.com.vescovi.base.mapstruct.cliente.mapper.ClienteMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController extends BaseController<Cliente, Long> {

    @NonNull private final ClienteService clienteService;
    @NonNull private final ClienteMapper clienteMapper;

    @Override
    protected BaseService<Cliente, Long> getService() {
        return clienteService;
    }

    @Override
    public Cliente save(@Valid Cliente entity) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping("postDTO")
    public ResponseEntity<ClienteDTO> postDTO(@Valid @RequestBody ClienteDTO clienteDTO){

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
//        Cliente cliente2 = ClienteMapper.INSTANCE.toEntity(clienteDTO);

        Cliente saved = clienteService.save(cliente);

        clienteDTO = clienteMapper.toDTO(saved);

        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
    }

    @PutMapping("putDTO/{id}")
    public ResponseEntity<ClienteDTO> putDTO(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable("id") Long id){
        Cliente cliforSave = Optional.ofNullable(
                clienteService.findById(id)
        ).orElseThrow(() -> new ClienteBadRequestException("cliente nao encontrado"));

//        Cliente cliente = clienteMapper.toEntity(clienteDTO);

        Cliente cliente2 = ClienteMapper.INSTANCE.toEntity(clienteDTO);

        Cliente saved = clienteService.save(cliente2);

        clienteDTO = clienteMapper.toDTO(saved);

        return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
    }

}
