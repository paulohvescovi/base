package br.com.vescovi.base.mapstruct.cliente;

import br.com.vescovi.base.util.ClienteCreator;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
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
        Cliente clienteForSave = ClienteCreator.createClienteParaSalvar();
        Cliente clienteSaved = clienteRepository.save(clienteForSave);

        Assertions.assertThat(clienteSaved).isNotNull();
        Assertions.assertThat(clienteSaved.getId()).isNotNull();
        Assertions.assertThat(clienteSaved.getNome()).isEqualTo(clienteForSave.getNome());

    }

    @Test
    @DisplayName("Testando atualizar cliente no banco de dados")
    void save_UpdateCliente_WhenSuccessful(){
        Cliente clienteForSave = ClienteCreator.createClienteParaSalvar();
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
        Cliente clienteForSave = ClienteCreator.createClienteParaSalvar();
        Cliente clienteSaved = clienteRepository.save(clienteForSave);

        clienteRepository.delete(clienteSaved);

        Optional<Cliente> clienteOptional = this.clienteRepository.findById(clienteSaved.getId());

        Assertions.assertThat(clienteOptional).isEmpty();
    }

    @Test
    @DisplayName("Buscando pelo  nome no banco de dados")
    void findByNome_ReturnListOfCliente_WhenSuccessful(){
        Cliente clienteforSave1 = ClienteCreator.createClienteParaSalvar();
        Cliente clienteSaved1 = clienteRepository.save(clienteforSave1);

        List<Cliente> clienteList = clienteRepository.findFirstByNomeContaining("Cliente");

        Assertions.assertThat(clienteList).isNotEmpty();
        Assertions.assertThat(clienteList).contains(clienteforSave1);
        Assertions.assertThat(clienteList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Buscando pelo nome que nao existe no banco de dados")
    void findByNome_ReturnEmptyListOfCliente_WhenSuccessful(){
        Cliente clienteforSave1 = ClienteCreator.createClienteParaSalvar();
        Cliente clienteSaved1 = clienteRepository.save(clienteforSave1);

        List<Cliente> clienteList = clienteRepository.findFirstByNomeContaining("Paulo");

        Assertions.assertThat(clienteList).isNotNull();
        Assertions.assertThat(clienteList).isEmpty();
    }

    @Test
    @DisplayName("Salvando cliente sem nome, deve lancar ConstraintValidationException")
    void save_throwConstraintExceotion_WhenNomeIsNull(){
        Cliente clienteForSave = new Cliente();

//        Assertions.assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
//            @Override
//            public void call() throws Throwable {
//                clienteRepository.save(clienteForSave);
//            }
//        }).isInstanceOf(ConstraintViolationException.class);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> clienteRepository.save(clienteForSave))
                .withMessageContaining("nome do cliente nao pode ser vazio");
    }

}