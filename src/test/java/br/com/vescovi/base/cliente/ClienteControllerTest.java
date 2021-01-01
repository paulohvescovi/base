package br.com.vescovi.base.cliente;

import br.com.vescovi.base.cliente.mapper.ClienteMapper;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testes para o Controller de cliente")
class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock private ClienteService clienteServiceMock;
    @Mock private ClienteMapper clienteMapper;

    @BeforeEach
    void setup(){
        PageImpl<Cliente> clientePage = new PageImpl<>(List.of(ClienteCreator.createClienteValido()));
        BDDMockito
                .when(clienteServiceMock.findAll(ArgumentMatchers.any()))
                .thenReturn(clientePage);

        BDDMockito
                .when(clienteServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(ClienteCreator.createClienteValido());

        BDDMockito
                .when(clienteServiceMock.save(ArgumentMatchers.any(Cliente.class)))
                .thenReturn(ClienteCreator.createClienteValido());

        BDDMockito.doNothing().when(clienteServiceMock).delete(ArgumentMatchers.any(Cliente.class));

    }

    @Test
    @DisplayName("listando clientes pelo find all retornando paginacao")
    void list_ReturnsListOfClientesPageObjecj_When_successful(){
        Cliente clienteEsperado = ClienteCreator.createClienteValido();

        Page<Cliente> clientesRetornados = clienteController.find(0, 1);

        Assertions.assertThat(clientesRetornados)
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clientesRetornados.toList().get(0).getNome())
                .isEqualTo(clienteEsperado.getNome());

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente")
    void findById_ReturnsClienteById_When_successful(){
        Long idExperado = ClienteCreator.createClienteValido().getId();

        Cliente clienteEncontrado = clienteController.findById(1L);

        Assertions.assertThat(clienteEncontrado).isNotNull();
        Assertions.assertThat(clienteEncontrado.getId())
                .isNotNull()
                .isEqualTo(idExperado);

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente inexistente")
    void findById_ReturnsNull_WhenSuccessful(){
        BDDMockito
                .when(clienteServiceMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(null);

        Cliente clienteEncontrado = clienteController.findById(1L);

        Assertions.assertThat(clienteEncontrado).isNull();

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente")
    void save_ReturnsClienteSaved_WhenSuccessful(){
        Long idExperado = ClienteCreator.createClienteValido().getId();

        Cliente clienteEncontrado = clienteController.save(ClienteCreator.createClienteParaSalvar());

        Assertions.assertThat(clienteEncontrado).isNotNull();
        Assertions.assertThat(clienteEncontrado.getId())
                .isNotNull()
                .isEqualTo(idExperado);

    }

    @Test
    @DisplayName("Deletando cliente pelo id")
    void delete_RemoverCliente_WhenSuccessful(){

        Assertions.assertThatCode(() -> clienteController.delete(1L))
                .doesNotThrowAnyException();

        //se respondesse com ResponseEntity por exemplo
//        ResponseEntity<Void> entity = clienteController.delete(1L);
//        Assertions.assertThat(entity).isNotNull();
//        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }

}