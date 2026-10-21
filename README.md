# 🎮 FreePlayZone

> **Plataforma Web Full Stack para catálogo, gerenciamento e recomendação inteligente de jogos gratuitos com IA Generativa.**

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![React](https://img.shields.io/badge/React.js-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![TailwindCSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Google Gemini](https://img.shields.io/badge/Gemini_AI-4285F4?style=for-the-badge&logo=google&logoColor=white)

---

## 🏛️ Arquitetura e Estrutura do Projeto

O repositório segue uma organização Full Stack limpa e desacoplada entre API e cliente web:

```text
FreePlayZone/
├── back/            # Backend API RESTful em Java 17+ e Spring Boot 3
└── front/           # Interface Web em React.js, Vite e Tailwind CSS
```

### 🛡️ Destaques Técnicos & Decisões de Engenharia

1. **Autenticação e Autorização Stateless (`back/`):**
   - Cadastro de usuários com hashing criptográfico de senhas (`BCrypt`).
   - Sessão stateless baseada em **JWT (JSON Web Token)** validada via filtro de segurança customizado no **Spring Security**.
2. **Motor de Recomendação com IA Generativa (`back/`):**
   - Integração com a **Google Gemini API** para Processamento de Linguagem Natural (NLP), interpretando preferências descritas em texto livre pelo usuário.
   - Cruzamento inteligente dos parâmetros extraídos pela IA com o catálogo global da **RAWG API** para retornar sugestões precisas.
3. **Persistência Relacional e Mapeamento:**
   - Modelagem relacional com **Spring Data JPA / Hibernate** sobre **PostgreSQL / MySQL**, garantindo integridade na gestão de bibliotecas pessoais de jogos.
4. **Experiência de Usuário Reativa (`front/`):**
   - SPA construída em **React.js** com **Vite** e **Tailwind CSS**, utilizando **Axios** (com interceptors de autenticação JWT) e **React Router DOM** para navegação protegida.

---

## ⚡ Como Executar o Projeto

### Pré-requisitos
- JDK 17+ e Maven 3.8+
- Node.js 18+ e npm
- Banco de dados relacional (PostgreSQL ou MySQL)
- Chaves de API da [RAWG](https://rawg.io/apidocs) e do [Google AI Studio (Gemini)](https://aistudio.google.com/)

### 1. Clonar o repositório
```bash
git clone https://github.com/TacioMoreira25/FreePlayZone.git
cd FreePlayZone
```

### 2. Configurar e Iniciar o Backend (`back/`)
Configure as variáveis de ambiente no `back/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/freeplayzone
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
api.rawg.key=SUA_CHAVE_RAWG
api.gemini.key=SUA_CHAVE_GEMINI
jwt.secret=SUA_CHAVE_SECRETA_JWT
```

Execute a API:
```bash
cd back
./mvnw clean install
./mvnw spring-boot:run
```
> A API ficará disponível em `http://localhost:8080`.

### 3. Configurar e Iniciar o Frontend (`front/`)
Em outro terminal:
```bash
cd front
npm install
npm run dev
```
> A aplicação web estará acessível em `http://localhost:5173`.
