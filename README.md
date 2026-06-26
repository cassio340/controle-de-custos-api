# 💰 Gestão de Custos

API REST para gerenciamento de despesas pessoais e rurais, desenvolvida com Spring Boot e PostgreSQL.

## 📋 Sobre o Projeto

O **Gestão de Custos** é uma aplicação back-end que permite aos usuários registrar, categorizar e controlar suas despesas. Cada despesa pode ser associada a um usuário e a um estabelecimento (merchant), com suporte a categorias que abrangem desde gastos domésticos até despesas agrícolas e pecuárias.

## ✨ Funcionalidades

- Cadastro de usuários
- Gerenciamento de despesas
- Cadastro de estabelecimentos
- Categorização de despesas
- Tratamento de exceções personalizado
- Documentação com Swagger/OpenAPI
- Containerização com Docker
- Deploy em produção no Render

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
  - Spring Web MVC
  - Spring Data JPA
  - Spring Validation
  - Spring DevTools
- **PostgreSQL**
- **Lombok**
- **Maven**

## 🚀 Deploy

A aplicação está disponível em produção.

**API**

https://controle-custos-api.onrender.com

**Swagger**

https://controle-custos-api.onrender.com/swagger-ui/index.html

> ⚠️ **Observação:** Esta é a versão atual do projeto. A autenticação e autorização com Spring Security e JWT ainda estão em desenvolvimento. Portanto, os endpoints ainda não possuem proteção por autenticação.

## 📁 Estrutura do Projeto

```
src/main/java/br/com/cassio340/gestaodecustos/
├── controllers/
│   ├── ExpenseController.java
│   ├── MerchantController.java
│   └── UserController.java
├── dto/
│   ├── ExpenseRequest.java
│   ├── ExpenseResponse.java
│   ├── MerchantRequest.java
│   ├── MerchantResponse.java
│   ├── UserRequest.java
│   └── UserResponse.java
├── entities/
│   ├── enums/
│   │   └── Category.java
│   ├── Expense.java
│   ├── Merchant.java
│   └── User.java
├── exceptions/
│   ├── custom/
│   │   ├── BadRequestException.java
│   │   ├── DataBaseException.java
│   │   └── ResourceNotFoundException.java
│   ├── handler/
│   │   └── ResourceExceptionHandler.java
│   └── response/
│       └── StandardError.java
├── mapper/
│   ├── ExpenseMapper.java
│   ├── MerchantMapper.java
│   └── UserMapper.java
├── repositories/
│   ├── ExpenseRepository.java
│   ├── MerchantRepository.java
│   └── UserRepository.java
├── services/
│   ├── ExpenseService.java
│   ├── MerchantService.java
│   └── UserService.java
└── GestaoDeCustosApplication.java
```

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- Java 21+
- Maven 3.8+
- PostgreSQL

### Banco de Dados

Crie um banco de dados no PostgreSQL:

```sql
CREATE DATABASE Gestao_de_custos;
```

Configure o arquivo `src/main/resources/application-dev.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Gestao_de_custos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Executando o Projeto

```bash
# Clone o repositório
git clone https://github.com/cassio340/gestao-de-custos.git

# Entre na pasta do projeto
cd gestao-de-custos

# Execute com Maven
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## 🐳 Executando com Docker

```bash
docker pull cassio340/controle-custos-api:1.0

docker run -p 8080:8080 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://<HOST>:5432/<DATABASE> \
-e SPRING_DATASOURCE_USERNAME=<USUARIO> \
-e SPRING_DATASOURCE_PASSWORD=<SENHA> \
cassio340/controle-custos-api:1.0
```

## 🔗 Endpoints

### Usuários — `/users`

| Método | Endpoint      | Descrição               |
|--------|---------------|-------------------------|
| GET    | `/users`      | Lista todos os usuários |
| GET    | `/users/{id}` | Busca usuário por ID    |
| POST   | `/users`      | Cria novo usuário       |
| PUT    | `/users/{id}` | Atualiza usuário        |
| DELETE | `/users/{id}` | Remove usuário          |

**Exemplo de corpo (POST/PUT):**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "password": "senha123"
}
```

---

### Estabelecimentos — `/merchants`

| Método | Endpoint           | Descrição                     |
|--------|--------------------|-------------------------------|
| GET    | `/merchants`       | Lista todos os estabelecimentos |
| GET    | `/merchants/{id}`  | Busca estabelecimento por ID  |
| POST   | `/merchants`       | Cadastra estabelecimento      |
| PUT    | `/merchants/{id}`  | Atualiza estabelecimento      |
| DELETE | `/merchants/{id}`  | Remove estabelecimento        |

**Exemplo de corpo (POST/PUT):**
```json
{
  "name": "Mercado Central"
}
```

---

### Despesas — `/expenses`

| Método | Endpoint          | Descrição              |
|--------|-------------------|------------------------|
| GET    | `/expenses`       | Lista todas as despesas |
| POST   | `/expenses`       | Registra nova despesa   |
| PUT    | `/expenses/{id}`  | Atualiza despesa        |
| DELETE | `/expenses/{id}`  | Remove despesa          |

**Exemplo de corpo (POST/PUT):**
```json
{
  "name": "Compras da semana",
  "amount": 350.00,
  "category": "FOOD",
  "merchantId": 1,
  "userId": 1
}
```

## 🏷️ Categorias de Despesa

| Categoria         | Descrição                  |
|-------------------|----------------------------|
| `FOOD`            | Alimentação                |
| `VEHICLE`         | Veículo                    |
| `GAS_STATION`     | Posto de combustível       |
| `ENERGY`          | Energia elétrica           |
| `WATER`           | Água                       |
| `INTERNET`        | Internet                   |
| `HOUSING`         | Moradia                    |
| `HEALTH`          | Saúde                      |
| `EDUCATION`       | Educação                   |
| `ENTERTAINMENT`   | Entretenimento             |
| `AGRICULTURE`     | Agricultura                |
| `LIVESTOCK`       | Pecuária                   |
| `FARM_SUPPLIES`   | Insumos agrícolas          |
| `ANIMAL_FEED`     | Ração animal               |
| `VETERINARY`      | Veterinário                |
| `FARM_EQUIPMENT`  | Equipamentos rurais        |
| `FARM_MAINTENANCE`| Manutenção rural           |
| `TAXES`           | Impostos e taxas           |
| `DEBTS`           | Dívidas                    |
| `OTHER`           | Outros                     |

## ⚠️ Tratamento de Erros

A API retorna erros padronizados no seguinte formato:

```json
{
  "timestamp": "2026-06-09T12:00:00Z",
  "status": 404,
  "error": "Resource Not Found",
  "message": "Recurso não encontrado",
  "path": "/expenses/99"
}
```

| Status | Descrição                              |
|--------|----------------------------------------|
| 400    | Requisição inválida / Erro no banco    |
| 404    | Recurso não encontrado                 |
| 405    | Método HTTP não suportado             |

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.