package br.com.vescovi.base.mapstruct.cliente;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para cliente Repository")
class ClienteRepositoryTest {

    @Autowired private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Testando salvar cliente no banco de dados")
    void save_PersistCliente_WhenSuccessful(){
        Cliente clienteForSave = createCliente();
        Cliente clienteSaved = clienteRepository.save(clienteForSave);

        Assertions.assertThat(clienteSaved).isNotNull();
        Assertions.assertThat(clienteSaved.getId()).isNotNull();
        Assertions.assertThat(clienteSaved.getNome()).isEqualTo(clienteForSave.getNome());

    }

    private Cliente createCliente(){
        return Cliente.builder()
                .nome("Cliente test jpa")
                .cnpj("64181424000182")
                .build();
    }
}