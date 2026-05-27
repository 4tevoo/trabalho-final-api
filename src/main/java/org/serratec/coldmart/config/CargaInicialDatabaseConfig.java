package org.serratec.coldmart.config;

import jakarta.transaction.Transactional;
import org.serratec.coldmart.entity.Categoria;
import org.serratec.coldmart.entity.Curso;
import org.serratec.coldmart.repository.CategoriaRepository;
import org.serratec.coldmart.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargaInicialDatabaseConfig {

    @Bean
    @Transactional
    CommandLineRunner carregarDB(CategoriaRepository categoriaRepository, CursoRepository cursoRepository) {
        return args -> {
            cursoRepository.deleteAll();
            categoriaRepository.deleteAll();

            Categoria fundamentos = new Categoria();
            fundamentos.setNome("Fundamentos");
            fundamentos.setDescricao("Base fundamental para o entendimento das áreas da programação.");
            fundamentos = categoriaRepository.save(fundamentos);

            Categoria frontend = new Categoria();
            frontend.setNome("Frontend");
            frontend.setDescricao("Parte visual e interativa de um site ou aplicativo.");
            frontend = categoriaRepository.save(frontend);

            Categoria backend = new Categoria();
            backend.setNome("Backend");
            backend.setDescricao("Parte de um sistema ou site que opera nos bastidores, invisível para o usuário.");
            backend = categoriaRepository.save(backend);

            Categoria mobile = new Categoria();
            mobile.setNome("Mobile");
            mobile.setDescricao("Criação de aplicativos, sites e softwares focados em dispositivos móveis.");
            mobile = categoriaRepository.save(mobile);

            Curso logica = new Curso();
            logica.setDescricao("Conhecimento fundamental para a introdução na programação.");
            logica.setPreco(59.99);
            logica.setTitulo("Lógica de Programação");
            logica.setCategoria(fundamentos);
            cursoRepository.save(logica);

            Curso versionamento = new Curso();
            versionamento.setDescricao("Conhecimento sobre versionamento de código.");
            versionamento.setPreco(59.99);
            versionamento.setTitulo("Git");
            versionamento.setCategoria(fundamentos);
            cursoRepository.save(versionamento);

            Curso front = new Curso();
            front.setDescricao("Conhecimento em HTML, CSS e Javascript para a construção de aplicações web");
            front.setPreco(199.99);
            front.setTitulo("Frontend Essencial");
            front.setCategoria(frontend);
            cursoRepository.save(front);

            Curso framework = new Curso();
            framework.setDescricao("Conhecimento sobre o principal framework do mercado, para desenvolvimento web.");
            framework.setPreco(199.99);
            framework.setTitulo("React");
            framework.setCategoria(frontend);
            cursoRepository.save(framework);

            Curso java = new Curso();
            java.setDescricao("Conhecimento sobre POO (Programação Orientada a Objetos) com a linguagem Java.");
            java.setPreco(199.99);
            java.setTitulo("POO Java");
            java.setCategoria(backend);
            cursoRepository.save(java);

            Curso banco = new Curso();
            banco.setDescricao("Conhecimento sobre SGBD(Sistema de Gerenciamento de Banco de Dados) PostgreSQL, para construção e manutenção.");
            banco.setPreco(199.99);
            banco.setTitulo("Banco de Dados");
            banco.setCategoria(backend);
            cursoRepository.save(banco);

            Curso api = new Curso();
            api.setDescricao("Conhecimento sobre Api Rest com uso do framework SpringBoot, utilizando a linguagem Java.");
            api.setPreco(199.99);
            api.setTitulo("Api Rest");
            api.setCategoria(backend);
            cursoRepository.save(api);

            Curso app = new Curso();
            app.setDescricao("Conhecimento sobre desenvolvimento mobile.");
            app.setPreco(199.99);
            app.setTitulo("React-Native");
            app.setCategoria(mobile);
            cursoRepository.save(app);
        };
    }
}
