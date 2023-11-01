package br.com.locadora.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
            .title("Locadora")
            .version("1.0")
            .description("Documentação sobre os endpoints da API da locadora de filmes, permitindo uma fácil integração e compreensão dos recursos disponíveis."));
    }

}
