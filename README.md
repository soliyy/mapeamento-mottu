#  API Mapeamento Inteligente de Pátio - Challenge Mottu

Este projeto é uma API REST desenvolvida como parte do Challenge da empresa **Mottu**, utilizando **Spring Boot**, que permite o gerenciamento de uma frota de motos, seus sensores, movimentações, pátios, operadores e filiais.

A API oferece recursos completos para CRUD, filtros, paginação, ordenação, cache e documentação Swagger.

##  Funcionalidades

-  CRUD completo para:
  - Filiais
  - Pátios
  - Motos
  - Operadores
  - Sensores
  - Movimentações
-  Relacionamentos entre as entidades
-  Validações de dados (Bean Validation)
-  Filtros de busca:
  - Buscar **Moto** por **Status**
  - Buscar **Pátio** por **Descrição**
  - Buscar **Operador** por **Nome**
-  Paginação e ordenação nos endpoints
-  Cache para otimizar listagens
-  Tratamento centralizado de erros
-  API documentada com **Swagger**
-  Banco de dados em memória **H2**

##  Tecnologias e Ferramentas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Web
- Spring Validation
- Spring Cache
- H2 Database (banco em memória)
- Swagger (OpenAPI - Springdoc)
- Maven

##  Documentação Swagger

Após rodar o projeto, acesse:

http://localhost:8080/swagger-ui/index.html

- Através do Swagger é possível testar todos os endpoints da API de forma interativa.

##  Console do Banco de Dados H2

Acesse:

http://localhost:8080/h2-console

###  Dados de acesso:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `admin`
- **Password:** `123456`

##  Endpoints principais

###  Moto
- `POST /api/motos` → Criar moto
- `GET /api/motos` → Listar motos (com paginação e ordenação)
- `GET /api/motos/{placa}` → Buscar moto por placa
- `PUT /api/motos/{placa}` → Atualizar moto
- `DELETE /api/motos/{placa}` → Deletar moto
- `GET /api/motos/buscar-por-status?status=Ativa` → Buscar moto por status

###  Filial
- `POST /api/filiais`
- `GET /api/filiais`
- `GET /api/filiais/{id}`
- `PUT /api/filiais/{id}`
- `DELETE /api/filiais/{id}`

###  Pátio
- `POST /api/patios`
- `GET /api/patios`
- `GET /api/patios/{id}`
- `PUT /api/patios/{id}`
- `DELETE /api/patios/{id}`
- `GET /api/patios/buscar-por-descricao?descricao=Centro` → Buscar pátio por descrição

###  Operador
- `POST /api/operadores`
- `GET /api/operadores`
- `GET /api/operadores/{id}`
- `PUT /api/operadores/{id}`
- `DELETE /api/operadores/{id}`
- `GET /api/operadores/buscar-por-nome?nome=João` → Buscar operador por nome

###  Movimentação
- `POST /api/movimentacoes`
- `GET /api/movimentacoes`
- `GET /api/movimentacoes/{id}`
- `PUT /api/movimentacoes/{id}`
- `DELETE /api/movimentacoes/{id}`

###  Sensor
- `POST /api/sensores`
- `GET /api/sensores`
- `GET /api/sensores/{id}`
- `PUT /api/sensores/{id}`
- `DELETE /api/sensores/{id}`

##  Como executar o projeto

###  Pré-requisitos
- Java 17 instalado
- Maven instalado

###  Passos para rodar:
1. Clone este repositório:
```bash
git  clone https://github.com/soliyy/mapeamento-mottu.git
```
2. Acesse o diretório do projeto:
```bash
cd mapeamento
```
3. Execute o projeto:
```bash
mvn spring-boot:run
```
4. Acesse o Swagger:
```
http://localhost:8080/swagger-ui/index.html
```
5. Acesse o H2 console:
```
http://localhost:8080/h2-console
```

##  Estrutura de Pacotes

```
com.mottu.mapeamento
├── config           → Configurações Swagger e Cache
├── controller       → Controllers da API (endpoints REST)
├── dto              → Data Transfer Objects (DTOs)
├── entity           → Entidades JPA
├── exception        → Tratamento de exceções
├── repository       → Repositórios JPA
├── service          → Regras de negócio (Services)
└── MapeamentoApplication.java
```

##  Desenvolvido para:
Mottu | FIAP 

##  Desenvolvido por:  
Lucas Martins Soliman - RM: 556281
Diego Bassalo Canals Silva - RM: 558710
Pedro henrique jorge de paula - RM: 558833
