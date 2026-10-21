# 🎮 FreePlayZone - Backend API

API RESTful desenvolvida em **Java** e **Spring Boot** para catálogo, busca e recomendação inteligente de jogos gratuitos, integrando processamento de linguagem natural com a **Gemini API (Google)** e a base de dados global da **RAWG API**.

---

## 🚀 Tecnologias Utilizadas

- **Linguagem & Framework:** Java 17+, Spring Boot 3
- **Segurança & Autenticação:** Spring Security com autenticação stateless baseada em **JWT (JSON Web Token)**
- **Persistência de Dados:** Spring Data JPA e Hibernate
- **Banco de Dados:** PostgreSQL / MySQL (Relacional)
- **Integrações de Inteligência Artificial & Dados:**
  - **Google Gemini API:** Processamento de linguagem natural para recomendação dinâmica com base no perfil do jogador
  - **RAWG Video Games Database API:** Catálogo global de metadados, capas, notas e informações de jogos
- **Gerenciador de Dependências:** Maven

---

## 🏛️ Arquitetura e Funcionalidades

A aplicação adota arquitetura em camadas orientada a boas práticas e separação de responsabilidades:

1. **Autenticação & Autorização:**
   - Cadastro de usuários com senhas criptografadas (BCrypt).
   - Login com geração de token JWT assinado.
   - Filtro de segurança customizado interceptando requisições para validar permissões de acesso às rotas protegidas.

2. **Catálogo de Jogos & Integrações Externas:**
   - Comunicação síncrona com a RAWG API para consulta de jogos gratuitos com paginação e filtros de gênero/plataforma.
   - Tratamento de contratos e conversão de payloads JSON externos para DTOs internos coesos.

3. **Motor de Recomendação com IA:**
   - Endpoint dedicado que recebe as preferências descritas pelo usuário em texto aberto.
   - Engenharia de Prompts estruturada enviada ao Google Gemini para extração semântica dos critérios ideais de jogos.
   - Cruzamento dos resultados da IA com a base de dados de jogos para entregar sugestões assertivas.

---

## ⚡ Como Executar o Projeto

### Pré-requisitos
- JDK 17 ou superior
- Maven 3.8+
- Banco de dados relacional (PostgreSQL ou MySQL)
- Chave de API da [RAWG API](https://rawg.io/apidocs)
- Chave de API do [Google AI Studio (Gemini)](https://aistudio.google.com/)

### 1. Clonar o repositório
```bash
git clone https://github.com/TacioMoreira25/FreePlayZone.git
cd FreePlayZone
```

### 2. Configurar variáveis de ambiente
Configure as seguintes variáveis no seu arquivo `application.properties` (ou variáveis de sistema):

```properties
# Banco de Dados
spring.datasource.url=jdbc:postgresql://localhost:5432/freeplayzone
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Chaves de API de terceiros
api.rawg.key=SUA_CHAVE_RAWG
api.gemini.key=SUA_CHAVE_GEMINI

# Segurança JWT
jwt.secret=SUA_CHAVE_SECRETA_JWT_AQUI
jwt.expiration=86400000
```

### 3. Compilar e Executar
```bash
# Baixar dependências e compilar
./mvnw clean install

# Iniciar a aplicação
./mvnw spring-boot:run
```
A API estará disponível por padrão na porta `8080` (`http://localhost:8080`).
