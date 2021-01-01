package br.com.vescovi.base.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    @NotEmpty(message = "nome do cliente nao pode ser vazio")
    private String nome;

    @Column(name = "cnpj", length = 200, nullable = false, unique = true)
    @CNPJ(message = "cnpj invalido")
    private String cnpj;

}
