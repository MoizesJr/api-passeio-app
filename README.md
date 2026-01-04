# ⚙️ API Passeio App — Backend Engine (Spring Boot)

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Google_Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white" alt="Google Login" />
</div>

<br />

> **Core Engine:** API REST robusta desenvolvida para gerenciar o ecossistema do Passeio App. Este backend lida com a persistência de dados, regras de negócio e a integração de autenticação via Google.

---

## 🚀 Endpoints da API

Abaixo estão listadas as rotas organizadas por contexto. A API utiliza **CORS configurado** para permitir acessos externos do front-end Angular.

### 🏝️ Lugares (`/lugares`)
Gerencia o catálogo de destinos turísticos.
* `GET /lugares` — Lista todos os lugares. Suporta filtros:
    * `?nome=...` — Busca por nome.
    * `?categoria=...` — Filtra por ID da categoria.
* `GET /lugares/{id}` — Retorna os detalhes de um lugar específico.
* `POST /lugares` — 🔐 Cadastra um novo lugar (Requer Validação).
* `PUT /lugares/{id}` — 🔐 Atualiza informações de um lugar existente.
* `DELETE /lugares/{id}` — 🔐 Remove um lugar do sistema.

### 📂 Categorias (`/categorias`)
Gerencia as classificações (Ex: Praias, Museus, Restaurantes).
* `GET /categorias` — Lista todas as categorias.
* `GET /categorias/{id}` — Busca categoria por ID.
* `POST /categorias` — 🔐 Cria uma nova categoria (Status: **201 Created**).
* `PUT /categorias/{id}` — 🔐 Atualiza dados da categoria.
* `DELETE /categorias/{id}` — 🔐 Remove uma categoria (Status: **204 No Content**).

### 👤 Autenticação e Usuários (`/auth`)
Gerencia o acesso e perfis de permissão.
* `POST /auth/login` — 🔐 **Login via Google**: Processa o objeto `Usuario` vindo do Google Cloud.
* `GET /auth/usuarios` — Lista todos os perfis cadastrados.
* `POST /auth/usuarios` — Criação manual de usuários.
* `PUT /auth/usuarios/{id}` — Atualiza o perfil (Útil para mudar nível de acesso para **ADMIN**).
* `DELETE /auth/usuarios/{id}` — Remove um usuário do sistema.

---

## 🛠️ Stack Técnica & Padrões

* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Segurança & Validação:** * `Jakarta Validation` para garantir integridade via `@Valid`.
    * `@CrossOrigin` habilitado para integração total com o front-end.
* **Persistência:** Camada de Service isolada garantindo que o Controller gerencie apenas as requisições HTTP.
* **Status Codes:** Implementação rigorosa do padrão REST (200, 201, 204).

---

## ⚙️ Como executar o Backend

1. **Pré-requisitos:**
   - Java 17 ou superior.
   - Maven instalado.
   - Banco de Dados PostgreSQL.

2. **Configuração do Ambiente:**
   Configure o arquivo `src/main/resources/application.properties` com suas credenciais:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
