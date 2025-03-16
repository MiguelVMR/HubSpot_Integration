# API de Integração com HubSpot

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Docker](https://img.shields.io/badge/Docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 🛠️ Configuração e Execução do Projeto

### 📂 Pré-requisitos

- Docker instalado

### 📥 Clonando o repositório

```sh
git clone https://github.com/MiguelVMR/HubSpot_Integration.git
cd HubSpot_Integration/
```

### 📌 Configuração das Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto e configure suas variáveis com base no arquivo `.env.example` fornecido.

### 🚀 Subindo os containers

Certifique-se de que as portas 8080, 4040 e 5432 estejam livres antes de executar:

```sh
docker compose up --build
```

A API estará disponível em `http://localhost:8080`

### 🔍 Documentação da API (Swagger)

Após a execução, a documentação interativa estará disponível em:

```
http://localhost:8080/swagger-ui.html
```

### 🔄 Fluxo da API

1. **Autenticação:**
    - Realize login via `/login`.
    - Copie o token da resposta e utilize-o no Swagger (botão "Authorize") ou inclua-o no cabeçalho Authorization como `Bearer {token}` ao usar ferramentas como Postman.
    - Utilize o usuário padrão cadastrado ou crie um novo. O login padrão é `admin` e a senha é configurada no `.env`.

2. **Integração com HubSpot:**
    - Acesse `/hubspot/authorize` para gerar a URL de autenticação OAuth2.
    - Faça login com sua conta HubSpot.
    - O HubSpot redirecionará para uma URL contendo um código de autorização, utilizado para gerar um Access Token.
    - Esse token é armazenado temporariamente em cache (Caffeine) para maior segurança, prevenindo vazamentos no banco de dados.

3. **Manipulação de Contatos:**
    - Crie contatos via `/contacts/create`.
    - Liste os contatos registrados no HubSpot em `/contacts`.

4. **Webhooks:**
    - O HubSpot envia eventos de criação de contato (`contact.creation`) automaticamente.
    - Os eventos podem ser consultados em `/hubspot/webhook-contact-create-data`.

### 🔒 Observações

Alguns endpoints não estão documentados no Swagger, pois são internos:
- `GET /callback`
- `POST /hubspot/webhook-contact-create-data`

---

## 📚 Bibliotecas Utilizadas e Justificativas

### **Segurança e Autenticação**
- `spring-boot-starter-security` e `spring-boot-starter-oauth2-resource-server`: Controle de acesso e autenticação via OAuth2.

### **Persistência de Dados**
- `spring-boot-starter-data-jpa`: Facilidade na manipulação de dados com JPA e Hibernate.
- `postgresql`: Driver para conexão com o banco de dados PostgreSQL.

### **Gerenciamento de Variáveis de Ambiente**
- `dotenv-java`: Simplifica a configuração de variáveis de ambiente no ambiente local.

### **Desempenho e Cache**
- `caffeine`: Armazena dados sensíveis temporariamente em memória para aumentar a segurança e reduzir acessos ao banco de dados.

### **Validação de Dados**
- `spring-boot-starter-validation`: Garante a integridade dos dados enviados para a API.

### **Documentação**
- `springdoc-openapi-starter-webmvc-ui`: Gera automaticamente a documentação da API no Swagger.

### **Testes**
- `spring-boot-starter-test` e `spring-security-test`: Desenvolvimento de testes unitários e de segurança.

---

## 🔮 Melhorias Futuras

- Modularização da API em microsserviços para maior escalabilidade.
- Implementação de padrões de comunicação entre microsserviços, como **Event-Carried State Transfer** e **Saga Pattern (Coreografia)**.
- Integração com sistemas de filas (Kafka ou RabbitMQ) para maior resiliência.
- Uso do **Resilience4j** para Circuit Breaker e tratamento de falhas.
- Monitoramento e logging avançados com **Spring Boot Actuator**.
- Implementação de **observabilidade** com ferramentas como Grafana, DataDog, entre outras.
- Melhorias na camada de cache utilizando **Redis** para otimização do desempenho.
- Testes End-to-End com **TestContainers**.
- Centralização de configurações utilizando **Global Config Management Pattern**.

---
