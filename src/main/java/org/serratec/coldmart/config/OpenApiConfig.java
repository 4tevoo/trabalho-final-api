package org.serratec.coldmart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI config() {

        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Api de venda de cursos");

        Contact contact = new Contact();
        contact.setEmail("grupo06@residente.serratec.org");
        contact.setName("Grupo 06: Leilton Braga, Estêvão Viana, Yuri Martins, Jhonata Raibolt, José Ricardo Mello e Yasmin Veríssimo");

        Info info = new Info()
                .contact(contact)
                .description("API REST")
                .title("API do sistema ColdMart")
                .version("1.0.0");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
