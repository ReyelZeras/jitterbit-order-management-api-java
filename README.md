Jitterbit Order Management API (Java/Spring Boot)

API desenvolvida para o desafio técnico de gerenciamento de pedidos, convertida de Node.js para Java 17 com Spring Boot 3.

🛠 Tecnologias e Decisões Técnicas

Java 17 & Spring Boot 3: Utilização das versões mais recentes e estáveis.

Spring Data JPA: Abstração da camada de persistência para PostgreSQL.

Spring Security: Implementação de Basic Auth (usuário: admin, senha: admin123).

Records (Java 14+): Utilizados para DTOs, garantindo imutabilidade e redução de boilerplate.

OpenAPI (Swagger): Documentação interativa da API.

Docker: Ambiente isolado para o banco de dados.

🚀 Como Executar

Pré-requisitos

Docker & Docker Compose

JDK 17

Maven 3.8+

1. Subir o Banco de Dados

Execute o comando especificado para o container PostgreSQL:

docker run --name pg_jitterbit -e POSTGRES_USER=user_jitterbit -e POSTGRES_PASSWORD=pass_jitterbit -e POSTGRES_DB=db_orders -p 5435:5432 -d postgres


2. Rodar a Aplicação

mvn spring-boot:run


A API estará disponível em http://localhost:3000.

📖 Documentação (Swagger)

Com a aplicação rodando, acesse:
http://localhost:3000/swagger-ui.html

📡 Endpoints Principais

Pedidos (/order)

POST /order: Cria um novo pedido (recebe JSON em português).

GET /order/{orderId}: Busca dados de um pedido específico.

GET /order/list: Lista todos os pedidos.

PUT /order/{orderId}: Atualiza um pedido.

DELETE /order/{orderId}: Remove um pedido.

🔒 Autenticação

A API utiliza Basic Authentication.

User: admin

Password: admin123

🔄 Transformação de Dados

A API realiza o mapeamento automático (Data Transformation) entre o JSON de entrada (campos em português) e a persistência no banco de dados (campos em inglês conforme requisito).

Desenvolvido como parte do desafio técnico Jitterbit.