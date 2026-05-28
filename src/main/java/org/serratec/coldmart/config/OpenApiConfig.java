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
        server.setDescription("Servidor Local - Venda de cursos");

        String descricao = """
                Bem-vindo à documentação oficial da API REST do **ColdMart**, uma plataforma de venda de cursos online.
                
                Este sistema foi desenvolvido com foco em performance e boas práticas, permitindo o gerenciamento completo de categorias, cursos, clientes e pedidos.
                
                Desenvolvedores (Grupo 06):
                * Estêvão Viana
                * Jhonata Raibolt
                * José Ricardo Mello
                * Leilton Braga
                * Yasmin Veríssimo
                * Yuri Martins
                
                """;

        Info info = new Info()
                .title("API do sistema ColdMart")
                .version("1.0.0")
                .description(descricao);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
