# Sistema de Gestão Acadêmica — API REST + Frontend

API Back-End para gerenciamento acadêmico de uma instituição de ensino, com autenticação e controle de acesso por papéis, além de um frontend simples para consumo da API.

> **Versão atual:** v3.1.0 — containerização com Docker Compose (backend, frontend e PostgreSQL).

## Sobre o projeto

A aplicação gerencia o relacionamento entre alunos, professores, cursos e turmas, oferecendo operações de cadastro, consulta, ativação/desativação e matrícula. O projeto nasceu como uma aplicação de terminal e passou por três grandes evoluções: a migração para uma API REST (v2.0.0), a adição de autenticação, autorização por papéis e um frontend HTML/CSS/JS consumindo essa API (v3.0.0), e a containerização de todo o sistema com Docker Compose (v3.1.0).

## Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.0.6**
- **Spring Data JPA / Hibernate**
- **Spring Security** — autenticação e autorização
- **JJWT** — geração e validação de tokens JWT
- **PostgreSQL**
- **Bean Validation (Jakarta Validation)**
- **Lombok**
- **Maven**
- **HTML, CSS e JavaScript puro** (frontend, sem frameworks)
- **Docker e Docker Compose** — empacotamento e orquestração dos serviços
- **Nginx** — servidor do frontend e proxy reverso para a API

## Arquitetura

```
Controller  →  Service  →  Repository  →  Model (Entity)
     ↑             ↓
   DTO ←──────── DTO
```

- **`controller`** — expõe os endpoints REST.
- **`service`** — regras de negócio, uma classe por ação.
- **`repository`** — interfaces `JpaRepository`, com métodos derivados sempre que possível.
- **`models`** — entidades JPA.
- **`dto`** — objetos de entrada (`*RequestDto`) e saída (`*ResponseDto`).
- **`exception`** — exceções de negócio + `GlobalExceptionHandler` centralizado (`@RestControllerAdvice`).
- **`security`** — `JwtService`, `UsuarioDetailsService`, `JwtAuthFilter` — responsáveis pela autenticação stateless via token.
- **`config`** — `SecurityConfig`, `CorsConfig`, `PasswordEncoderConfig`, `AdminSeeder`.
- **`frontend/`** — páginas HTML consumindo a API via `fetch`, com CSS e JS organizados por módulo.

## Autenticação e Autorização

A API usa **JWT (JSON Web Token)** em um modelo *stateless* — nenhuma sessão é mantida no servidor; cada requisição se autentica sozinha através de um token enviado no header `Authorization`.

### Papéis (roles)

| Papel | Descrição |
|---|---|
| `ADMIN` | Único no sistema, criado automaticamente na inicialização. Pode gerenciar outros usuários (cadastrar, editar, desativar). Não pode ser modificado ou desativado por ninguém. |
| `USUARIO` | Criado apenas por um `ADMIN`. Tem acesso pleno aos módulos acadêmicos (Aluno, Curso, Professor, Turma), mas não pode gerenciar outros usuários. |

### Fluxo de autenticação

1. `POST /login` com `email` e `senha` retorna um token JWT válido por 24 horas, junto com `email` e `role`.
2. O token deve ser enviado em toda requisição subsequente no header:
   ```
   Authorization: Bearer <token>
   ```
3. Requisições sem token válido retornam `401 Unauthorized`.
4. Requisições autenticadas, mas sem permissão suficiente (ex: `USUARIO` tentando acessar rotas de `/usuarios`), retornam `403 Forbidden`.

### Usuário administrador

Criado automaticamente na primeira inicialização da aplicação (`AdminSeeder`), a partir das variáveis de ambiente `ADMIN_EMAIL` e `ADMIN_SENHA`. Não é possível criar, editar ou desativar o admin pela API — apenas ele existe com esse papel.

## Modelagem (entidades)

### Usuario
| Campo | Tipo |
|---|---|
| id | Long |
| email | String (único) |
| senha | String (hash BCrypt) |
| role | Role (`ADMIN` / `USUARIO`) |
| status | Status (`ATIVADA` / `DESATIVADA`) |

### Aluno
| Campo | Tipo |
|---|---|
| ra | Long |
| nome | String |
| dataNascimento | LocalDate |
| email | String (único) |

### Professor
| Campo | Tipo |
|---|---|
| id | Long |
| nome | String |
| especialidade | Especialidade (enum) |
| email | String (único) |
| telefone | String |
| status | Status |

### Curso
| Campo | Tipo |
|---|---|
| id | Long |
| nome | String |
| descricao | String |
| cargaHoraria | Integer |
| status | Status |

### Turma
| Campo | Tipo |
|---|---|
| id | Long |
| nome | String |
| data | LocalDate |
| professor | Professor (relacionamento) |
| curso | Curso (relacionamento) |
| status | Status |

### Matrícula
| Campo | Tipo |
|---|---|
| id | Long |
| aluno | Aluno (relacionamento) |
| turma | Turma (relacionamento) |
| dataMatricula | LocalDate |

## Como executar

Existem três formas de rodar o projeto. A mais simples é com Docker.

### Variáveis de ambiente

Todas as formas usam as mesmas variáveis. Copie o arquivo de exemplo e preencha com seus valores:

```bash
cp .env.example .env
```

| Variável | Descrição |
|---|---|
| `DB_USER` / `DB_PASSWORD` | Credenciais do PostgreSQL |
| `JWT_SECRET` | Chave usada para assinar os tokens. Deve ser Base64. Gere com `openssl rand -base64 64` |
| `ADMIN_EMAIL` / `ADMIN_SENHA` | Credenciais do usuário administrador criado na primeira inicialização |

O arquivo `.env` contém segredos e está no `.gitignore`. Nunca o envie ao repositório.

### Opção 1 — Docker Compose, construindo as imagens localmente

Requer apenas o Docker instalado.

```bash
docker compose up --build
```

Quando o log mostrar `Started SistemagestaoacademicaApplication` e `Usuário ADMIN criado com sucesso`, acesse **http://localhost** e entre com o `ADMIN_EMAIL` e o `ADMIN_SENHA` definidos no `.env`.

Para encerrar:

```bash
docker compose down
```

Os dados do banco não persistem entre execuções: a cada `up`, o PostgreSQL começa vazio e o administrador é recriado.

### Opção 2 — Docker Compose, usando as imagens do Docker Hub

Não exige clonar o código-fonte, apenas o arquivo `docker-compose.hub.yml` e o `.env`:

```bash
docker compose -f docker-compose.hub.yml up
```

As imagens usadas são `SEU_USUARIO/gestao-academica-backend` e `SEU_USUARIO/gestao-academica-frontend`.

### Opção 3 — Desenvolvimento local, sem Docker

1. PostgreSQL disponível, com um database `instituicao_academica`.
2. Definir as variáveis de ambiente acima, mais `DB_HOST` (ex: `localhost`).
3. Rodar `SistemagestaoacademicaApplication`. A API sobe em `http://localhost:8080`.
4. Abrir qualquer página de `frontend/` com a extensão **Live Server** do VS Code. A porta do Live Server (`5501`) precisa estar liberada em `CorsConfig` (`allowedOrigins`).

## Arquitetura de containers

```
                  porta 80 (única exposta)
                          |
                 +--------v--------+
                 |    frontend     |   Nginx: serve HTML/CSS/JS
                 |     (Nginx)     |   e repassa /api/* ao backend
                 +--------+--------+
                          | rede interna do Docker
                 +--------v--------+
                 |     backend     |   Spring Boot, porta 8080
                 +--------+--------+   (não exposta ao host)
                          |
                 +--------v--------+
                 |    postgres     |   PostgreSQL 16
                 +-----------------+
```

- O **Nginx** é o único serviço exposto ao host. Frontend e API são servidos pela mesma origem, o que elimina problemas de CORS nesse cenário.
- Chamadas do navegador para `/api/...` são repassadas ao backend, que as recebe sem o prefixo (`/api/alunos` chega como `/alunos`).
- O backend usa um **multi-stage build**: o Maven compila o projeto em um estágio e apenas o `.jar` segue para a imagem final, baseada em JRE, que é menor.
- O `api.js` detecta o ambiente: na porta `5501` (Live Server) chama `http://localhost:8080` diretamente; em qualquer outra, usa `/api`.

## Endpoints

Todas as rotas abaixo, exceto `/login`, exigem o header `Authorization: Bearer <token>`.

### Autenticação

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| POST | `/login` | Público | Autentica com `email`+`senha`, retorna token JWT |

### Usuários — `/usuarios` (somente ADMIN)

| Método | Rota | Descrição |
|---|---|---|
| POST | `/usuarios` | Cadastra novo usuário (sempre `role: USUARIO`) |
| GET | `/usuarios/ativos` | Lista usuários ativos |
| GET | `/usuarios/desativados` | Lista usuários desativados |
| PATCH | `/usuarios/{id}` | Edita email/senha de um usuário |
| PATCH | `/usuarios/{id}/desativar` | Desativa um usuário (soft delete) |

### Aluno — `/alunos`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/alunos` | Cadastra um novo aluno |
| GET | `/alunos/turma/{turmaId}` | Lista os alunos matriculados em uma turma |
| POST | `/alunos/matricula` | Matricula um aluno em uma turma |

### Curso — `/cursos`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/cursos` | Cadastra um novo curso |
| GET | `/cursos/ativados` | Lista cursos ativos |
| GET | `/cursos/desativados` | Lista cursos desativados |
| PATCH | `/cursos/{cursoId}/ativar` | Ativa um curso |
| PATCH | `/cursos/{cursoId}/desativar` | Desativa um curso |

### Professor — `/professores`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/professores` | Cadastra um novo professor |
| GET | `/professores/ativados` | Lista professores ativos |
| GET | `/professores/desativados` | Lista professores desativados |
| PATCH | `/professores/{professorId}/desativar` | Desativa um professor |

### Turma — `/turmas`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/turmas` | Cadastra uma nova turma |
| GET | `/turmas/ativadas` | Lista turmas ativas |
| GET | `/turmas/desativadas` | Lista turmas desativadas |
| PATCH | `/turmas/{turmaId}/ativar` | Ativa uma turma |
| PATCH | `/turmas/{turmaId}/desativar` | Desativa uma turma |

## Regras de negócio

- Um curso, professor, turma ou usuário pode ser **desativado**, mas não excluído — histórico preservado via soft delete.
- Não é permitido desativar um professor ou curso que possua turma(s) ativa(s) vinculada(s).
- Não é permitido **ativar** uma turma cujo professor ou curso vinculado esteja inativo.
- Não é permitido matricular um aluno duas vezes na mesma turma.
- Email é único para Aluno, Professor e Usuário.
- Uma entidade já desativada não pode ser desativada novamente; o mesmo vale para reativação.
- O usuário `ADMIN` nunca pode ser editado, desativado ou ter seu papel alterado — é único no sistema.

## Tratamento de erros

Exceções de negócio são capturadas centralmente por um `GlobalExceptionHandler`, retornando um formato padronizado:

```json
{
  "timestamp": "2026-09-20T14:32:10.123",
  "status": 409,
  "erro": "Conflict",
  "mensagem": "Email já cadastrado: maria.silva@email.com"
}
```

Erros de validação de campo (`@Valid`) retornam o detalhamento por campo:

```json
{
  "timestamp": "2026-09-20T10:15:00.123",
  "status": 400,
  "erro": "Bad Request",
  "campos": {
    "nome": "Nome é obrigatório",
    "email": "Email inválido"
  }
}
```

### Mapeamento de exceções por status HTTP

| Status | Exceções |
|---|---|
| 400 Bad Request | `DataInvalidaException`, `EmailInvalidoException`, erros de `@Valid` |
| 401 Unauthorized | `CredenciaisInvalidasException`, `UsuarioInativoException`, ausência/expiração de token |
| 403 Forbidden | Usuário autenticado sem permissão suficiente (ex: `USUARIO` acessando `/usuarios`) |
| 404 Not Found | `AlunoNaoEncontradoException`, `CursoNaoEncontradoException`, `ProfessorNaoEncontradoException`, `TurmaNaoEncontradaException`, `UsuarioNaoEncontradoException` |
| 409 Conflict | `CursoJaCadastradoException`, `CursoJaDesativadoException`, `EmailJaCadastradoException`, `MatriculaDuplicadaException`, `ProfessorComTurmaAtivaException`, `ProfessorJaDesativadoException`, `AdminNaoPodeSerModificadoException`, `ProfessorInativoException`, `CursoInativoException` |
| 422 Unprocessable Entity | `ListaCursosVazioException`, `ListaProfessorVazioException`, `ListaTurmaVaziaException`, `TurmaVaziaException` |

## Frontend

Interface em HTML, CSS e JavaScript puro (`frontend/`), sem frameworks ou bibliotecas externas de UI. Organização:

```
frontend/
  login.html            → tela de autenticação
  index.html            → dashboard / navegação
  alunos.html           → cadastro, matrícula e busca por turma
  cursos.html           → cadastro e gestão de status
  professores.html      → cadastro e gestão de status
  turmas.html           → cadastro e gestão de status
  usuarios.html         → gestão de usuários (endpoints restritos a ADMIN)
  css/
    style.css
  js/
    api.js               → fetch centralizado, injeta token JWT, trata 401 e escolhe a URL base conforme o ambiente
    auth-guard.js         → protege páginas, redireciona para login se não autenticado
    login.js
    alunos.js / cursos.js / professores.js / turmas.js / usuarios.js
  Dockerfile            → imagem Nginx com os arquivos estáticos
  nginx.conf            → servidor estático + proxy reverso para /api
```

O token JWT é armazenado no `localStorage` após o login e enviado automaticamente em toda chamada à API. Uma resposta `401` de qualquer endpoint limpa a sessão local e redireciona para a tela de login.

## Próximos passos

- [ ] Testes automatizados (unitários e de integração)
- [ ] Endpoint de "esqueci minha senha" / redefinição de senha
- [ ] Refresh token, para evitar que o usuário precise logar novamente a cada 24h
- [ ] Implementar exclusão definitiva (Curso/Turma/Aluno) respeitando vínculos ativos
- [ ] Cobrir o módulo de Matrícula com endpoint de "desmatricular"
- [ ] Link "Usuários" e botão de logout na sidebar, exibidos conforme o papel do usuário
- [ ] Endpoint para reativar usuários desativados
- [ ] Volume para persistir os dados do PostgreSQL entre execuções
- [ ] Healthcheck do banco no Compose, para o backend só iniciar quando o PostgreSQL estiver pronto
- [ ] Deploy da API e do frontend em ambiente acessível publicamente
