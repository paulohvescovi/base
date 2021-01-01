package br.com.vescovi.base.mapstruct.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findFirstByNomeContaining(String nome);

}
