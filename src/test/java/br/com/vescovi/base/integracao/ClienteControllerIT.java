package br.com.vescovi.base.integracao;

import br.com.vescovi.base.cliente.Cliente;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Testes de integracao cliente controller")
@AutoConfigureTestDatabase//usa o que ta em memoria pra teste de integracao
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

}
