package br.com.vescovi.base.mapstruct.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteData extends JpaRepository<Cliente, Long> {
}
