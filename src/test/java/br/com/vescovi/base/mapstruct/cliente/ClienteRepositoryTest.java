package br.com.vescovi.base.mapstruct.cliente;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes para cliente Repository")
@Log4j2
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

    @Test
    @DisplayName("Testando atualizar cliente no banco de dados")
    void save_UpdateCliente_WhenSuccessful(){
        Cliente clienteForSave = createCliente();
        Cliente clienteSaved = clienteRepository.save(clienteForSave);

        clienteSaved.setNome("Cliente Alterado");
        Cliente clienteAtualizado = clienteRepository.save(clienteSaved);

        Assertions.assertThat(clienteAtualizado).isNotNull();
        Assertions.assertThat(clienteAtualizado.getId()).isNotNull();
        Assertions.assertThat(clienteAtualizado.getNome()).isEqualTo(clienteSaved.getNome());

    }

    @Test
    @DisplayName("Testando deletar cliente no banco de dados")
    void delete_UpdateCliente_WhenSuccessful(){
        Cliente clienteForSave = createCliente();
        Cliente clienteSaved = clienteRepository.save(clienteForSave);

        clienteRepository.delete(clienteSaved);

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteSaved.getId());

        Assertions.assertThat(clienteOptional).isEmpty();
    }

    @Test
    @DisplayName("Buscando pelo  nome no banco de dados")
    void findByNome_ReturnListOfCliente_WhenSuccessful(){
        Cliente clienteforSave1 = createCliente();
        Cliente clienteSaved1 = clienteRepository.save(clienteforSave1);

        List<Cliente> clienteList = clienteRepository.findFirstByNomeContaining("Cliente");

        Assertions.assertThat(clienteList).isNotEmpty();
        Assertions.assertThat(clienteList).contains(clienteforSave1);
        Assertions.assertThat(clienteList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Buscando pelo nome que nao existe no banco de dados")
    void findByNome_ReturnEmptyListOfCliente_WhenSuccessful(){
        Cliente clienteforSave1 = createCliente();
        Cliente clienteSaved1 = clienteRepository.save(clienteforSave1);

        List<Cliente> clienteList = clienteRepository.findFirstByNomeContaining("Paulo");

        Assertions.assertThat(clienteList).isNotNull();
        Assertions.assertThat(clienteList).isEmpty();
    }

    private Cliente createCliente(){
        return Cliente.builder()
                .nome("Cliente test jpa")
                .cnpj("64181424000182")
                .build();
    }
}