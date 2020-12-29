package br.com.vescovi.base.configuration;

import br.com.vescovi.base.annotations.Producao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Producao
public class MinhaConfiguracaoBeanExemploProd {

    @Bean(name = "applicationNameBean")
    public String applicationNameBean(){
        return "Nome da aplicacao, pra poder injetar em algum lugar, exemplo de config bean profile prod";
    }

    @Bean
    public CommandLineRunner executarNoPerfilDev(){
        return args -> {
            System.out.println("Rodando em ambiente prod");
        };
    }

}
