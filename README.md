# 🚀 Sistema de Processamento Assíncrono com Retry e DLQ

<table align="center">
  <tr>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/rabbitmq/rabbitmq-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="70"/></td>
    <td><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apache/apache-original.svg" width="70"/></td>
  </tr>
</table>

Sistema robusto de processamento assíncrono com mecanismos de retentativa automática e Dead Letter Queue para mensagens com falha.

## ✨ Funcionalidades

- ✅ **API REST** para envio de mensagens
- ✅ **Processamento assíncrono** com RabbitMQ
- ✅ **Retry automático** com backoff exponencial (3 tentativas)
- ✅ **Dead Letter Queue (DLQ)** para falhas persistentes
- ✅ **Logs estruturados** em JSON para monitoramento
- ✅ **Resiliência** com simulação de erros (30% chance)
- ✅ **Docker Compose** para infraestrutura

## 🏗 Arquitetura
```
Cliente → API Spring Boot → RabbitMQ → Consumer → Processamento
↓
DLQ (em caso de falha)
```
## 🚀 Começando Rápido

### Pré-requisitos
```
- Java 17+
- Maven 3.8+
- Docker e Docker Compose
```
### 1. Iniciar RabbitMQ

```
docker-compose up -d
```
### 2. Compilar e executar
```
./mvnw clean compile
./mvnw spring-boot:run
```
### 3. Testar a API
```
curl -X POST http://localhost:8080/api/messages \
  -H "Content-Type: application/json" \
  -d "Mensagem de teste"
```
### 📊 Monitoramento
RabbitMQ Management: http://localhost:15672 (guest/guest)

Logs da aplicação: logs/application.log (formato JSON)

Health Check: http://localhost:8080/actuator/health

### 🔧 Configuração
Arquivo application.yml
```
server:
  port: 8080

spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    listener:
      simple:
        retry:
          enabled: true
          max-attempts: 3
          initial-interval: 1000ms
          multiplier: 2
          max-interval: 10000ms
```
### Política de Retry
1ª tentativa: Imediata

2ª tentativa: 1 segundo depois

3ª tentativa: 2 segundos depois

Após 3 falhas: Mensagem enviada para DLQ

### 📁 Estrutura do Projeto
```
src/main/java/com/hyus4ki/asyncpro/
├── AsyncProApplication.java      # Classe principal
├── config/
│   └── RabbitMQConfig.java       # Configuração RabbitMQ
├── controller/
│   └── MessageController.java    # API REST
├── dto/
│   └── ProcessMessageDTO.java    # Objeto de transferência
├── exception/
│   └── ProcessingException.java  # Exceções customizadas
├── listener/
│   └── MessageListener.java      # Consumidor de mensagens
└── service/
    ├── MessageProcessorService.java # Lógica de processamento
    └── DLQService.java           # Serviço de DLQ
```
### 🧪 Testando Resiliência
O sistema inclui simulação de erro (30% chance) para testar:

Retry automático

Roteamento para DLQ

Logging de erros

### 🔍 Exemplo de Logs
```
{
  "@timestamp": "2025-12-17T02:42:20.425Z",
  "message": "Processando mensagem: 62d493f9-73aa-49c2-af3f-c186ecc04e18",
  "level": "INFO",
  "application": "async-pro"
}
```

### Desenvolvido com ❤️ e ☕ por Pablo Carvalho

📧 Contato: devpablocarvalho@gmail.com

🔗 LinkedIn: www.linkedin.com/in/pablo-carvalho-140255260