package br.com.vescovi.base.mapstruct.cliente;

import br.com.vescovi.base.mapstruct.cliente.mapper.ClienteMapper;
import br.com.vescovi.base.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o service de cliente")
class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock private ClienteRepository clienteRepositoryMock;

    @BeforeEach
    void setup(){
        PageImpl<Cliente> clientePage = new PageImpl<>(List.of(ClienteCreator.createClienteValido()));
        BDDMockito
                .when(clienteRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(clientePage);

        BDDMockito
                .when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ClienteCreator.createClienteValido()));

        BDDMockito
                .when(clienteRepositoryMock.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(ClienteCreator.createClienteValido());

        BDDMockito.doNothing().when(clienteRepositoryMock).delete(ArgumentMatchers.any(Cliente.class));

    }

    @Test
    @DisplayName("listando clientes pelo find all retornando paginacao")
    void list_ReturnsListOfClientesPageObjecj_When_successful(){
        Cliente clienteEsperado = ClienteCreator.createClienteValido();

        Page<Cliente> clientesRetornados =  clienteService.findAll(PageRequest.of(0,1));

        Assertions.assertThat(clientesRetornados)
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clientesRetornados.toList().get(0).getNome())
                .isEqualTo(clienteEsperado.getNome());

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente")
    void findById_ReturnsClienteById_When_successful(){
        Cliente clienteExperado = ClienteCreator.createClienteValido();

        Optional<Cliente> clienteEncontrado = clienteRepositoryMock.findById(1L);

        Assertions.assertThat(clienteEncontrado).isNotNull()
            .isPresent()
            .isEqualTo(Optional.of(clienteExperado));

        Assertions.assertThat(clienteEncontrado.get().getId())
                .isNotNull()
                .isEqualTo(clienteExperado.getId());

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente inexistente")
    void findById_ReturnsNull_WhenSuccessful(){
        BDDMockito
                .when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Cliente clienteEncontrado = clienteService.findById(1L);

        Assertions.assertThat(clienteEncontrado).isNull();

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente")
    void save_ReturnsClienteSaved_WhenSuccessful(){
        Long idExperado = ClienteCreator.createClienteValido().getId();

        Cliente clienteEncontrado = clienteRepositoryMock.save(ClienteCreator.createClienteParaSalvar());

        Assertions.assertThat(clienteEncontrado).isNotNull();
        Assertions.assertThat(clienteEncontrado.getId())
                .isNotNull()
                .isEqualTo(idExperado);

    }

    @Test
    @DisplayName("Deletando cliente pelo id")
    void delete_RemoverCliente_WhenSuccessful(){
        Cliente clienteforDelete = ClienteCreator.createClienteValido();
        Assertions.assertThatCode(() -> clienteRepositoryMock.delete(clienteforDelete))
                .doesNotThrowAnyException();
    }

}