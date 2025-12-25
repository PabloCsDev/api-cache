# 🚀 Cache API — Spring Boot + Redis + Docker

<table align="center">
  <tr>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" width="70"/></td>
  </tr>
</table>

API REST profissional desenvolvida com **Spring Boot**, utilizando **Redis como cache distribuído** para otimização de performance, **Docker Compose** para infraestrutura e **arquitetura limpa**, pronta para uso em ambiente real e portfólio técnico.

---

## ✨ Funcionalidades

- ✅ **CRUD completo de produtos**
- ✅ **Cache com Redis** usando Spring Cache
- ✅ **Cache inteligente** (put, evict e cache miss)
- ✅ **Tratamento global de exceções**
- ✅ **API REST padronizada**
- ✅ **Docker e Docker Compose**
- ✅ **Java 17 + Spring Boot 3**
- ✅ **Arquitetura em camadas**
- ✅ **Mensagens de erro claras (404, 400)**

---

## 🏗 Arquitetura
```
Controller → Service → Repository → Database
↓
Redis Cache
```

- **Controller**: expõe endpoints REST
- **Service**: regra de negócio + cache
- **Repository**: persistência com JPA
- **Redis**: cache distribuído
- **Exception Handler**: padronização de erros

---

## 🚀 Começando Rápido

### Pré-requisitos
```
- Java 17+
- Maven 3.8+
- Docker
- Docker Compose
```
---

### 1️⃣ Subir a infraestrutura (Redis)

```
docker compose up -d
```
2️⃣ Build da aplicação
```
mvn clean package
```
3️⃣ Subir a API
```
docker compose up --build
```
A aplicação estará disponível em:
```
http://localhost:8080
```
🔗 Endpoints da API
➕ Criar produto
```
curl -X POST http://localhost:8080/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Notebook","price":3500}'
  ```
📄 Buscar produto por ID (com cache)
```
curl http://localhost:8080/products/1
```
🔥 A primeira chamada consulta o banco
⚡ As próximas vêm direto do Redis

✏ Atualizar produto (invalida cache)
```
curl -X PUT http://localhost:8080/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Notebook RTX","price":5200}'
  ``` 
❌ Produto não encontrado
```
curl http://localhost:8080/products/999
Resposta:
```
```
{
  "message": "Product not found"
}
```
🧠 Estratégia de Cache
```
@Cacheable → leitura otimizada

@CachePut → atualização direta no cache

@CacheEvict → invalidação correta

Cache desacoplado da regra de negócio

Redis rodando em container isolado
```

⚙️ Configuração (application.yml)
```
server:
  port: 8080

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  cache:
    type: redis

  data:
    redis:
      host: redis
      port: 6379
```
📁 Estrutura do Projeto
```
src/main/java/com/hyus4ki/cacheapi
├── CacheApiApplication.java
├── controller/
│   └── ProductController.java
├── service/
│   └── ProductService.java
├── repository/
│   └── ProductRepository.java
├── model/
│   └── Product.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ProductNotFoundException.java
└── config/
    └── CacheConfig.java
``` 
🐳 Docker Compose
Redis isolado

API containerizada

Rede interna entre serviços

Fácil replicação em qualquer ambiente

📌 Objetivo do Projeto
Este projeto demonstra, de forma prática:

Uso real de cache distribuído

Boas práticas com Spring Boot

Organização de código profissional

Conhecimento de Docker

API pronta para produção

👨‍💻 Desenvolvido por
Pablo Carvalho

☕ Desenvolvedor Back-end
💻 Java | Spring Boot | Redis | Docker

📧 Email: devpablocarvalho@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/pablo-carvalho-140255260
