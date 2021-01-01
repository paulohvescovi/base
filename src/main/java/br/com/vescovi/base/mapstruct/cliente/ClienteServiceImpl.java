package br.com.vescovi.base.mapstruct.cliente;

import br.com.vescovi.base.framework.service.BaseServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long> implements ClienteService {

    @NonNull private final ClienteRepository clienteRepository;

    @Override
    protected JpaRepository<Cliente, Long> getData() {
        return clienteRepository;
    }
}
