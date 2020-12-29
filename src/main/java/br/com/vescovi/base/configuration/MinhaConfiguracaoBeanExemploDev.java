package br.com.vescovi.base.configuration;

import br.com.vescovi.base.annotations.Desenvolvimento;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Desenvolvimento
public class MinhaConfiguracaoBeanExemploDev {

    @Bean(name = "applicationNameBean")
    public String applicationNameBean(){
        return "Nome da aplicacao, pra poder injetar em algum lugar, exemplo de config bean profile dev";
    }

    @Bean
    public CommandLineRunner executarNoPerfilDev(){
        return args -> {
            System.out.println("Rodando em ambiente dev");
        };
    }

}
