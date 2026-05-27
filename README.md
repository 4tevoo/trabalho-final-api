# Trabalho Final de API RESTful
## E-Commerce | Coldmart — Educação Digital

Projeto de uma API RESTful desenvolvida com **Java** e **Spring Boot** para um e-commerce focado na venda de cursos online na área de tecnologia.

---

## Como Rodar o Projeto

### 1. Variáveis de Ambiente
Tanto as credenciais do banco de dados quanto as do servidor de e-mail são gerenciadas por variáveis de ambiente por questões de segurança. Para o envio de e-mails, utilizamos o **Mailtrap** (para evitar configurações complexas e chatas de segurança do Gmail).

#### Configuração via IntelliJ IDEA:
1. Clique na seta ao lado do botão de *Run* (Executar) no topo da IDE e selecione **Edit Configurations...**
2. Na janela que se abrir, clique em **Modify Options** e selecione **Environment Variables**.
3. Adicione as seguintes chaves e seus respectivos valores:
    * `USER_BD`: Seu usuário do PostgreSQL (ex: `postgres`)
    * `SENHA_BD`: Sua senha do PostgreSQL
    * `MAIL_USER`: Seu usuário/token do Mailtrap
    * `MAIL_PASSWORD`: Sua senha/token do Mailtrap
4. Clique em **Apply** e depois em **OK**.

> **Nota:** Se optar por não utilizar o Mailtrap, lembre-se de alterar as propriedades de host e porta no arquivo `application.properties`:
> ```properties
> spring.mail.host=seu_host_aqui
> spring.mail.port=sua_porta_aqui
> ```

---

## Banco de Dados

O projeto utiliza o **PostgreSQL**.
1. Crie um banco de dados local com o nome `ecommerce`.
2. Caso prefira utilizar outro nome, ajuste a URL de conexão no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/NOME_DO_SEU_BANCO
   
---

## Link do Swagger
```properties
http://localhost:8080/swagger-ui/index.html
```
---

## Tecnologias Utilizadas
Para fazer o sistema rodar direitinho com todas as validações e integrações, utilizamos:
- **Java 17 & Spring Boot 3.5.41** (Base do projeto)
  Spring Validation (Para as regras de @NotNull, @CPF, @Email direto nos DTOs)
- **Spring Mail** (Para envio das notificações de e-mail)
- **OpenFeign** (Para consumir de forma muito simples a API externa do ViaCEP)
- **PostgreSQL** (Nosso banco de dados relacional local)
- **Lombok** (Para não precisar digitar Getters, Setters e construtores em todo arquivo)
- **Springdoc OpenAPI (Swagger UI)** (Para gerar a documentação interativa das rotas automaticamente)
