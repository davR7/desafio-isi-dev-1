# 📌 Estudo de Caso SENAI/PE – Desenvolvedor Fullstack I
 
Aplicação fullstack do mercado de jogadores de uma liga de futebol fictícia.

**Pollify** é uma aplicação web para criação e participação em enquetes. O projeto foi desenvolvido com foco em uma autenticação segura baseada em JWT, utilizando access token de curta duração e refresh token para renovação automática da sessão, além de uma experiência simples e intuitiva para criar, gerenciar e participar de enquetes.

## ✨ Principais Funcionalidades

### Backend

- Cadastro de jogadores com cálculo de **overall** e **valor base** a partir dos atributos
- Edição de nome, posição, agilidade e idade, e alteração da condição física
- **Valor de mercado** calculado a cada consulta (condição, agilidade e idade)
- Exclusão lógica de jogadores livres
- Listagem de jogadores
- Criação e listagem de clubes, com caixa inicial de 300,00
- Validação de dados no backend, com mensagens de erro padronizadas (400, 404 e 409)

### Frontend

- Interface em React para listar jogadores e clubes

## 🏗️ Arquitetura

A estrutura geral do projeto é dividida em:

```text
root/
├── frontend -> Vite React
├── backend -> Spring Boot
└── nginx
```

Cada aplicação possui suas próprias configurações, dependências e variáveis de ambiente.

## 📚 Endpoints

### Visão geral do Backend

#### Status do Backend

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/health` | Verifica a disponibilidade do backend |

#### Clubes (clubs)

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/clubs` | Cria um club |
| GET  | `/clubs` | Lista clubs  |
| POST | `/clubs/id` | Busca um club especifico |

#### Jogadores (players)

| Método | Endpoint | Descrição |
|---|----|---|
| POST   | `/players` | Cria uma jogador |
| GET    | `/players` | Lista jogadores   |
| GET    | `/players/:id` | Busca um jogador especifico |
| PUT    | `/players/:id` | Atualiza um jogador especifico |
| DELETE | `/players/:id` | Remove uma jogador |
| PATCH  | `/players/:id/condition` | Atualiza condição de um jogador especifico |
| POST   | `/players/:id/buy` | Cria uma jogador |

## 📚 Visão geral do frontend

| Método | Endpoint | Descrição |
|---|----|---|
| GET    | `/players` | Lista jogadores |
| GET    | `/clubs`   | Lista clubs     |


## 🚀 Executando o projeto

1 - Clone o projeto, instale as dependências e configure os arquivos `.env`, verifique o env_example.

2 - Com o docker instalado em sua maquina execute: docker compose up --build

3 - Acesse:

   - Frontend: http://localhost
   - API: http://localhost:8080/api/v1

---

Desenvolvido por **Davidson Ratis** · [GitHub](https://github.com/davR7) · [LinkedIn](https://www.linkedin.com/in/davidson-ratis/)
