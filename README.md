# Sistema de Gestão Acadêmica — API REST

API Back-End para gerenciamento acadêmico de uma instituição de ensino, permitindo o controle de alunos, professores, cursos, turmas e matrículas.

> **Status do projeto:** em refatoração — migração de aplicação console (terminal) para API REST.

## Sobre o projeto

A aplicação gerencia o relacionamento entre alunos, professores e cursos, oferecendo operações de cadastro, consulta, atualização (ativação/desativação) e matrícula. O sistema foi originalmente construído como uma aplicação de terminal e está sendo refatorado para uma API REST testável via Postman, mantendo as regras de negócio já validadas e reorganizando a arquitetura em camadas desacopladas.

## Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.0.6**
- **Spring Data JPA / Hibernate**
- **PostgreSQL**
- **Bean Validation (Jakarta Validation)** — validação de DTOs de entrada
- **Lombok** — redução de boilerplate (`@RequiredArgsConstructor`)
- **Maven**

## Arquitetura

O projeto segue uma separação em camadas, com injeção de dependência via construtor:

```
Controller  →  Service  →  Repository  →  Model (Entity)
     ↑             ↓
   DTO ←──────── DTO
```

- **`controller`** — expõe os endpoints REST (`@RestController`), recebe/valida DTOs de entrada e retorna DTOs de saída com o `ResponseEntity` apropriado.
- **`service`** — concentra as regras de negócio, organizadas por ação (ex: `service/aluno/CadastrarAluno`, `service/curso/DesativarCurso`). Cada ação de negócio é uma classe própria.
- **`repository`** — interfaces `JpaRepository`, com métodos derivados (`existsBy...`, `findBy...`) sempre que possível, evitando `@Query` manual.
- **`models`** — entidades JPA (`@Entity`), sem nenhuma lógica de apresentação.
- **`dto`** — objetos de entrada (`*RequestDto`) e saída (`*ResponseDto`), evitando expor as entidades diretamente na API.
- **`exception`** — exceções de negócio customizadas + `GlobalExceptionHandler` centralizado (`@RestControllerAdvice`), que traduz cada exceção em uma resposta HTTP padronizada.

## Modelagem (entidades)

### Aluno
| Campo | Tipo |
|---|---|
| ra | Long (gerado automaticamente) |
| nome | String |
| dataNascimento | LocalDate |
| email | String (único) |

### Professor
| Campo | Tipo |
|---|---|
| id | Long (gerado automaticamente) |
| nome | String |
| especialidade | Especialidade (enum) |
| email | String (único) |
| telefone | String |
| status | Status (ATIVADA / DESATIVADA) |

### Curso
| Campo | Tipo |
|---|---|
| id | Long (gerado automaticamente) |
| nome | String |
| descricao | String |
| cargaHoraria | Integer |
| status | Status (ATIVADA / DESATIVADA) |

### Turma
| Campo | Tipo |
|---|---|
| id | Long (gerado automaticamente) |
| nome | String |
| data | LocalDate (gerada automaticamente) |
| professor | Professor (relacionamento) |
| curso | Curso (relacionamento) |
| status | Status (ATIVADA / DESATIVADA) |

### Matrícula
| Campo | Tipo |
|---|---|
| id | Long (gerado automaticamente) |
| aluno | Aluno (relacionamento) |
| turma | Turma (relacionamento) |
| dataMatricula | LocalDate (gerada automaticamente) |

**Relacionamentos:**
- Aluno (1) → (N) Matrícula
- Turma (1) → (N) Matrícula
- Curso (1) → (N) Turma
- Professor (1) → (N) Turma
- Uma matrícula é única por par (aluno, turma) — restrição aplicada em banco e validada em nível de serviço.

## Como executar

1. Ter um banco PostgreSQL disponível, com um database `instituicao_academica`.
2. Configurar as variáveis de ambiente usadas em `application.properties`:
   - `DB_HOST`
   - `DB_USER`
   - `DB_PASSWORD`
3. Rodar a aplicação (`SistemagestaoacademicaApplication`). O Hibernate cria/atualiza as tabelas automaticamente (`ddl-auto=update`).
4. A aplicação sobe em `http://localhost:8080`.

## Endpoints

Todas as requisições e respostas usam JSON. Datas de entrada, quando aplicável, seguem o formato `dd/MM/yyyy`.

### Aluno — `/aluno`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/aluno` | Cadastra um novo aluno |
| GET | `/aluno/turma/{turmaId}` | Lista os alunos matriculados em uma turma |
| POST | `/aluno/matricula` | Matricula um aluno em uma turma |

**Exemplo — `POST /aluno`**
```json
{
  "nome": "Maria Silva",
  "dataNascimento": "15/03/2000",
  "email": "maria.silva@email.com"
}
```

**Exemplo — `POST /aluno/matricula`**
```json
{
  "alunoRa": 1,
  "turmaId": 1
}
```

### Curso — `/cursos`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/cursos` | Cadastra um novo curso |
| GET | `/cursos/ativados` | Lista cursos ativos |
| GET | `/cursos/desativados` | Lista cursos desativados |
| PATCH | `/cursos/{cursoId}/desativar` | Desativa um curso (soft delete) |

**Exemplo — `POST /cursos`**
```json
{
  "nome": "Análise e Desenvolvimento de Sistemas",
  "descricao": "Curso técnico voltado para formação em desenvolvimento de software",
  "cargaHoraria": 2400
}
```

### Professor — `/professores`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/professores` | Cadastra um novo professor |
| GET | `/professores/ativados` | Lista professores ativos |
| GET | `/professores/desativados` | Lista professores desativados |
| PATCH | `/professores/{professorId}/desativar` | Desativa um professor (soft delete) |

**Exemplo — `POST /professores`**
```json
{
  "nome": "Fernanda Oliveira Costa",
  "especialidade": "MATEMATICA",
  "email": "fernanda.costa@instituicao.com",
  "telefone": "11987654321"
}
```

### Turma — `/turmas`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/turmas` | Cadastra uma nova turma |
| GET | `/turmas/ativadas` | *(em validação)* Lista turmas ativas |
| GET | `/turmas/desativadas` | *(em validação)* Lista turmas desativadas |
| PATCH | `/turmas/{turmaId}/desativar` | *(em validação)* Desativa uma turma |

**Exemplo — `POST /turmas`**
```json
{
  "nome": "Turma A - Noturno",
  "idProfessor": 1,
  "idCurso": 1
}
```

> Turma nasce sempre com status `ATIVADA`, definido pelo próprio sistema — o cliente não controla esse valor na criação.

## Regras de negócio

- Um curso, professor ou turma pode ser **desativado**, mas não excluído — o histórico é preservado (soft delete via campo `status`).
- Não é permitido desativar um professor que possua turma(s) ativa(s) vinculada(s).
- Não é permitido matricular um aluno duas vezes na mesma turma.
- Email é único para Aluno e para Professor.
- Uma entidade já desativada não pode ser desativada novamente.

## Tratamento de erros

Todas as exceções de negócio são capturadas centralmente por um `@RestControllerAdvice` (`GlobalExceptionHandler`) e retornadas em um formato padronizado:

```json
{
  "timestamp": "2026-09-17T14:32:10.123",
  "status": 409,
  "erro": "Conflict",
  "mensagem": "Email já cadastrado: maria.silva@email.com"
}
```

Erros de validação de campo (`@Valid`) retornam um formato com o detalhamento por campo:

```json
{
  "timestamp": "2026-09-17T10:15:00.123",
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
| 404 Not Found | `AlunoNaoEncontradoException`, `CursoNaoEncontradoException`, `ProfessorNaoEncontradoException`, `TurmaNaoEncontradaException` |
| 409 Conflict | `CursoJaCadastradoException`, `CursoJaDesativadoException`, `EmailJaCadastradoException`, `MatriculaDuplicadaException`, `ProfessorComTurmaAtivaException`, `ProfessorJaDesativadoException` |
| 422 Unprocessable Entity | `ListaCursosVazioException`, `ListaProfessorVazioException`, `ListaTurmaVaziaException`, `TurmaVaziaException` |

## Próximos passos

- [ ] Validar e documentar os endpoints restantes de `Turma` (listar ativas/desativadas, desativar)
- [ ] Implementar validação de turma duplicada (mesmo nome + professor + curso)
- [ ] Implementar exclusão definitiva (Curso/Turma/Aluno) respeitando vínculos ativos
- [ ] Cobrir o módulo de Matrícula com endpoint de "desmatricular" (cancelamento sem apagar histórico)
- [ ] Exportar collection do Postman como documentação executável da API
