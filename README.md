# Identity & Access Management (IAM) Service

Este serviço é um microserviço Spring Boot responsável pelo Gerenciamento de Identidade e Acesso (IAM) dentro do ecossistema de aplicativos do restaurante. Ele lida com autenticação de usuários, gerenciamento de papéis (roles) e controle de acesso baseado em permissões (RBAC).

## Principais Funcionalidades

- **Autenticação baseada em JWT:** Gera e valida JSON Web Tokens para autenticação segura e stateless.
- **Gerenciamento de Usuários:** Operações CRUD para usuários.
- **Controle de Acesso por Papel (RBAC):** Gerenciamento de papéis (roles) e tipos de usuário para controle de permissões granulares.
- **Documentação da API:** A API é autodocumentada usando Springdoc (Swagger).
- **Migrações de Banco de Dados:** Utiliza Flyway para gerenciar a evolução do schema do banco de dados.
- **Comunicação Assíncrona:** Integração com RabbitMQ para troca de mensagens.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.1**
  - Spring Web
  - Spring Security (com OAuth2 Resource Server)
  - Spring Data JPA
  - Spring AMQP (para RabbitMQ)
- **Hibernate**
- **PostgreSQL** (ou outro banco de dados relacional)
- **Flyway:** Para migrações de banco de dados.
- **RabbitMQ:** Para mensageria.
- **Lombok:** Para reduzir código boilerplate.
- **MapStruct:** Para mapeamento de DTOs e Entidades.
- **Springdoc (Swagger):** Para documentação da API.

## Como Executar o Projeto

### Pré-requisitos

- JDK 21 ou superior
- Maven 3.9 ou superior
- Docker e Docker Compose (recomendado para rodar o banco de dados e o RabbitMQ)
- Um cliente de banco de dados (como DBeaver ou pgAdmin)

### Configuração

1.  **Clone o repositório:**
    ```bash
    git clone `https://github.com/KervinCandido/identity-access-management.git`
    cd identity-access-management
    ```

2.  **Configure as variáveis de ambiente:**
    O serviço é configurado através do arquivo `src/main/resources/application.yaml` e seus perfis (`dev`, `prod`). As seguintes variáveis de ambiente são necessárias para o perfil `dev`:

    - **Banco de Dados:**
      - `DB_HOST`: Host do banco de dados (padrão: `localhost`)
      - `DB_PORT`: Porta do banco de dados (padrão: `5432`)
      - `DB_NAME`: Nome do banco de dados (padrão: `restaurant-db`)
      - `DB_USER`: Usuário do banco de dados (padrão: `restaurant-user`)
      - `DB_PASSWORD`: Senha do banco de dados (padrão: `restaurant-password`)

    - **RabbitMQ:**
      - `MQ_HOST`: Host do RabbitMQ (padrão: `localhost`)
      - `MQ_PORT`: Porta do RabbitMQ (padrão: `5672`)
      - `MQ_USER`: Usuário do RabbitMQ (padrão: `restaurant-mq`)
      - `MQ_PASSWORD`: Senha do RabbitMQ (padrão: `password-mq`)

    Você pode criar um arquivo `.env` na raiz do projeto ou configurar essas variáveis diretamente no seu ambiente de execução.

3.  **Inicie os serviços de dependência (usando Docker):**
    Se houver um arquivo `docker-compose.yml` na raiz do projeto, você pode iniciar o PostgreSQL e o RabbitMQ com:
    ```bash
    docker-compose up -d
    ```

### Execução

1.  **Compile e execute a aplicação com o Maven:**
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=dev
    ```

2.  A aplicação estará disponível em `http://localhost:8080`.

## Endpoints da API

A documentação completa da API está disponível via Swagger UI.

- **URL da Documentação (Swagger UI):** <a href="http://localhost:8080/swagger-ui/index.html" target="_blank" rel="noopener noreferrer">http://localhost:8080/swagger-ui/index.html</a>
- **URL da Definição OpenAPI:** <a href="http://localhost:8080/v3/api-docs" target="_blank" rel="noopener noreferrer">http://localhost:8080/v3/api-docs</a>
- **URL do Actuator:** <a href="http://localhost:8080/actuator/health" target="_blank" rel="noopener noreferrer">http://localhost:8080/actuator/health</a>


### Principais Endpoints

- `POST /auth`: Autentica um usuário e retorna um token JWT.
- `GET /roles`: Lista todos os papéis (roles) disponíveis.
- `GET /user-types`: Lista todos os tipos de usuário.
- `GET /user`: Lista todos os usuários (requer permissão).
- `POST /user`: Cria um novo usuário (requer permissão).
- `GET /user/{id}`: Obtém detalhes de um usuário específico (requer permissão).

### Filas do RabbitMQ

- `restaurant.user.created`: Enviado quando um novo usuário é criado.
- `restaurant.user.updated`: Enviado quando um usuário existente é atualizado.
- `restaurant.user.deleted`: Enviado quando um usuário é deletado

---
