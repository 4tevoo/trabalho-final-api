# 🚀 Trabalho Final de API RESTful
## E-Commerce | Coldmart — Educação Digital

  > **Status do Projeto:** Finalizado

  Projeto de uma API RESTful desenvolvida com **Java** e **Spring Boot** para um e-commerce focado na venda de cursos online na área de tecnologia.

---

## 🛠️ Tecnologias Utilizadas
Para fazer o sistema rodar direitinho com todas as validações e integrações, utilizamos:
- **Java 17 & Spring Boot 3.5.41** (Base do projeto)
  Spring Validation (Para as regras de @NotNull, @CPF, @Email direto nos DTOs)
- **Spring Mail** (Para envio das notificações de e-mail)
- **OpenFeign** (Para consumir de forma muito simples a API externa do ViaCEP)
- **PostgreSQL** (Nosso banco de dados relacional local)
- **Lombok** (Para não precisar digitar Getters, Setters e construtores em todo arquivo)
- **Springdoc OpenAPI (Swagger UI)** (Para gerar a documentação interativa das rotas automaticamente)
  O projeto utiliza o **PostgreSQL**.

---

## 📱 Principais Funcionalidades

### 📦 Catálogo de Produtos
* **Gestão de Categorias:** Inserção, edição, listagem e exclusão de categorias.
* **Gestão de Produtos:** CRUD completo de produtos, categorias e listagens de produtos que exibem os dados de suas categorias.

### 👥 Gestão de Clientes
* **Cadastro e Edição:** Controle completo de clientes com validação de dados (CPF, E-mail, etc.).
* **Integração ViaCEP:** Preenchimento automático dos dados de endereço a partir do CEP informado via Spring Cloud OpenFeign.
* **Notificações por E-mail:** Disparo automático de e-mails informativos ao inserir ou alterar o registro de um cliente utilizando o Spring Mail.

### 🛍️ Processamento de Pedidos
* **Criação de Pedidos:** Geração de pedidos obrigatoriamente vinculados a um cliente.
* **Controle de Status:** Atualização de status do pedido gerenciada por `Enum` e com tratamento contra valores inválidos.

---

## ⚙️ Como Rodar o Projeto

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

## 👥 Integrantes do Grupo 6

*   **Estevão Viana** - [GitHub Profile](https://github.com/4tevoo)
*   **Jhonata Raibolt** - [GitHub Profile](https://github.com/jhonataraibolt)
*   **José Ricardo Mello** - [GitHub Profile](https://github.com/josemello26)
*   **Leilton Braga** - [GitHub Profile](https://github.com/LeiltonBraga)
*   **Yasmin Verissimo** - [GitHub Profile](https://github.com/yasmimverissimo)
*   **Yuri Martins** - [GitHub Profile](https://github.com/Y-M-dev)
