package br.com.vescovi.base.integracao;

import br.com.vescovi.base.cliente.Cliente;
import br.com.vescovi.base.cliente.ClienteDTO;
import br.com.vescovi.base.cliente.ClienteRepository;
import br.com.vescovi.base.util.ClienteCreator;
import br.com.vescovi.base.util.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de integracao cliente controller")
@AutoConfigureTestDatabase//usa o que ta em memoria pra teste de integracao
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)//recriando o banco a cada teste
public class ClienteControllerIT {

    @Autowired  private TestRestTemplate testRestTemplate;
    @LocalServerPort private int port;

    @Autowired private ClienteRepository clienteRepository;

    @Test
    @DisplayName("listando clientes pelo find all retornando paginacao")
    void list_ReturnsListOfClientesPageObjecj_When_successful(){
        Cliente clienteSaved = clienteRepository.save(ClienteCreator.createClienteParaSalvar());

        PageableResponse<Cliente> clientePage = testRestTemplate.exchange("/clientes?page=0&size=20", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Cliente>>() {
                }
        ).getBody();

        Assertions.assertThat(clientePage).isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(clientePage.toList().get(0).getNome())
                .isEqualTo(clienteSaved.getNome());

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente")
    void findById_ReturnsClienteById_When_successful(){
        Cliente clienteSaved = clienteRepository.save(ClienteCreator.createClienteParaSalvar());

        Cliente clienteEncontrado = testRestTemplate.exchange("/clientes/{Id}",
                HttpMethod.GET, null, Cliente.class, 1L).getBody();

        Assertions.assertThat(clienteEncontrado).isNotNull();
        Assertions.assertThat(clienteEncontrado.getId())
                .isNotNull()
                .isEqualTo(clienteSaved.getId());

    }

    @Test
    @DisplayName("Testando POST dto ")
    void postDTO_ReturnsClienteDTO_WhenSuccessful(){
        ClienteDTO clienteDTOParaSalvar = ClienteCreator.createClienteDTOParaSalvar();


        ResponseEntity<ClienteDTO> clienteDTOResponseEntity = testRestTemplate.exchange("/clientes/postDTO",
                HttpMethod.POST, new HttpEntity<>(clienteDTOParaSalvar), ClienteDTO.class, 1L);

        Assertions.assertThat(clienteDTOResponseEntity.getStatusCode())
                .isNotNull()
                .isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(clienteDTOResponseEntity.getBody())
                .isNotNull();

        Assertions.assertThat(clienteDTOResponseEntity.getBody().getNome())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(clienteDTOParaSalvar.getNome());

    }

    @Test
    @DisplayName("buscando pelo codigo do cliente inexistente")
    void findById_ReturnsNull_WhenSuccessful(){
        Cliente clienteEncontrado = testRestTemplate.exchange("/clientes/{Id}",
                HttpMethod.GET, null, Cliente.class, 2L).getBody();

        Assertions.assertThat(clienteEncontrado).isNull();
    }

    @Test
    @DisplayName("Deletando cliente pelo id")
    void delete_RemoverCliente_WhenSuccessful(){
        Cliente clienteSaved = clienteRepository.save(ClienteCreator.createClienteParaSalvar());

        ResponseEntity<Cliente> clienteEncontradoEntity = testRestTemplate.exchange("/clientes/{Id}",
                HttpMethod.DELETE, null, Cliente.class, 1L);

        Assertions.assertThat(clienteEncontradoEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(clienteEncontradoEntity.getBody())
                .isNull();

    }

}
