# ⚽ Penalty Tracker API

API REST desenvolvida em **Spring Boot** para gerenciamento de jogadores e cobranças de pênaltis em partidas de futebol. Ideal para análise de desempenho e estatísticas de jogadores com base em cobranças realizadas.

## 📚 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven
- Swagger (springdoc-openapi)

## 📦 Estrutura do Projeto

penalty-tracker/
├── controller/ # Camada de endpoints REST (Player, PenaltyKick)
├── dto/ # Transferência de dados entre API e cliente
├── model/ # Entidades JPA (Player, PenaltyKick)
├── repository/ # Repositórios JPA
├── service/ # Regras de negócio
├── application.yml # Configuração do banco e Swagger
└── PenaltyTrackerApplication.java

bash
Copiar
Editar

## 🔄 Endpoints Principais

### 🎮 Player

| Método | Rota              | Descrição                      |
|--------|-------------------|-------------------------------|
| GET    | `/api/players`    | Lista todos os jogadores      |
| GET    | `/api/players/{id}` | Detalhes de um jogador       |
| POST   | `/api/players`    | Cria um novo jogador          |
| PUT    | `/api/players/{id}` | Atualiza um jogador          |
| DELETE | `/api/players/{id}` | Remove um jogador            |

### 🎯 PenaltyKick

| Método | Rota                                 | Descrição                         |
|--------|--------------------------------------|----------------------------------|
| GET    | `/api/penalty-kicks`                | Lista todos os pênaltis          |
| GET    | `/api/penalty-kicks/{id}`           | Detalhes de um pênalti           |
| GET    | `/api/penalty-kicks/player/{id}`    | Lista pênaltis de um jogador     |
| POST   | `/api/penalty-kicks`                | Registra uma nova cobrança       |
| PUT    | `/api/penalty-kicks/{id}`           | Atualiza uma cobrança            |
| DELETE | `/api/penalty-kicks/{id}`           | Remove uma cobrança              |

## 🔐 Relacionamento

- Um `Player` pode ter várias `PenaltyKick` (OneToMany).
- Cada `PenaltyKick` pertence a um único `Player` (ManyToOne).

## 🚀 Como Rodar o Projeto

```bash
# Clone o projeto
git clone https://github.com/seu-usuario/penalty-tracker.git
cd penalty-tracker

# Configure seu banco PostgreSQL:
# Crie um banco chamado: penalty_tracker_db
# Usuário: admin | Senha: root (ou edite em application.yml)

# Build & Run
./mvnw spring-boot:run
Acesse a documentação Swagger em:
👉 http://localhost:8080/swagger-ui.html ou /swagger-ui/index.html

✅ Requisitos
Java 17+

PostgreSQL

Maven
