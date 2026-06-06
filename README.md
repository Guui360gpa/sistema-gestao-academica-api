# Escopo do Projeto: Sistema de Gestão Acadêmica

**Status do projeto:** Em andamento

## 1. Visão Geral

O projeto consiste no desenvolvimento de uma API Back-End para gerenciamento acadêmico de uma instituição de ensino. O sistema permitirá o controle de alunos, professores, cursos, turmas e matrículas, possibilitando a administração das informações acadêmicas de forma centralizada, organizada e segura.

A aplicação terá como principal objetivo gerenciar o relacionamento entre alunos, professores e cursos, permitindo operações de cadastro, consulta, atualização e remoção dos dados cadastrados.

## 2. Objetivos do Sistema

O sistema deverá permitir:

* Cadastro de alunos.
* Cadastro de professores.
* Cadastro de cursos.
* Cadastro de turmas.
* Realização de matrículas de alunos em cursos.
* Consulta de alunos matriculados.
* Associação de professores aos cursos.
* Exclusão de registros quando necessário.
* Organização das informações acadêmicas da instituição.

## 3. Entidades do Sistema

### Aluno

Representa os estudantes matriculados na instituição.

Possíveis atributos:

* RA
* nome
* email
* telefone
* dataNascimento

### Professor

Representa os docentes responsáveis pelos cursos.

Possíveis atributos:

* id
* nome
* email
* telefone
* especialidade

### Curso

Representa os cursos oferecidos pela instituição.

Possíveis atributos:

* id
* nome
* descrição
* cargaHoraria

### Turma

Representa uma oferta específica de um curso.

Possíveis atributos:

* id
* nome
* semestre
* ano
* curso_id
* professor_id

### Matricula

Representa o vínculo entre um aluno e um curso/turma.

Possíveis atributos:

* id
* aluno_ra
* turma_id
* dataMatricula
* status

## 4. Funcionalidades

### 4.1 Gestão de Alunos

#### Cadastrar Aluno

Permitir o registro de novos alunos no sistema.

Dados obrigatórios:

* Nome
* Data de nascimento
* telefone
* E-mail

#### Listar Alunos

Permitir consultar todos os alunos cadastrados.

Retornar:

* RA
* Nome
* E-mail
* telefone

#### Excluir Aluno

Permitir remover um aluno do sistema desde que não possua matrículas ativas.

### 4.2 Gestão de Cursos

#### Cadastrar Curso

Permitir registrar novos cursos oferecidos pela instituição.

Dados obrigatórios:

* Nome do curso
* Descrição
* Carga horária

#### Listar Cursos Cadastrados

Permitir consultar todos os cursos disponíveis.

Retornar:

* ID
* Nome
* Descrição
* Carga horária

#### Deletar Curso

Permitir excluir um curso que não possua turmas ou matrículas vinculadas.

### 4.3 Gestão de Professores

#### Cadastrar Professor em Determinado Curso

Permitir vincular um professor a um curso através de uma turma.

O sistema deverá verificar:

* Existência do professor.
* Existência do curso.
* Existência da turma.

#### Listar Professores

Permitir consultar todos os professores cadastrados.

Retornar:

* ID
* Nome
* Especialidade
* Curso associado

#### Deletar Professor

Permitir remover professores que não estejam vinculados a turmas ativas.

### 4.4 Gestão de Matrículas

#### Matricular Aluno em Determinado Curso

Permitir associar um aluno a uma turma de um curso.

Regras:

* O aluno deve existir.
* O curso deve existir.
* A turma deve existir.
* O aluno não pode possuir matrícula duplicada na mesma turma.

Ao concluir a matrícula, o sistema deverá gerar um registro na entidade Matrícula.

#### Listar Alunos Matriculados

Permitir consultar:

* Todos os alunos matriculados.
* Alunos matriculados por curso.
* Alunos matriculados por turma.

Informações retornadas:

* Nome do aluno
* Curso
* Turma
* Data da matrícula
* Status

#### Desmatricular Aluno

Permitir cancelar uma matrícula existente.

Ao invés de remover o registro, o sistema deverá alterar o status da matrícula para:

* ATIVA
* CANCELADA

Mantendo o histórico acadêmico do aluno.

### 4.5 Gestão de Turmas

#### Cadastrar Turma

Permitir criar novas turmas para um curso.

Dados obrigatórios:

* Nome da turma
* Curso
* Professor responsável
* Ano
* Semestre

#### Listar Turmas

Permitir visualizar todas as turmas cadastradas.

Retornar:

* ID
* Nome da Turma
* Curso
* Professor
* Ano
* Semestre

#### Excluir Turma

Permitir remover turmas que não possuam matrículas vinculadas.

## 5. Regras de Negócio

* Um aluno pode possuir várias matrículas.
* Uma matrícula pertence a apenas um aluno.
* Um curso pode possuir várias turmas.
* Uma turma pertence a um único curso.
* Um professor pode ministrar várias turmas.
* Uma turma possui apenas um professor responsável.
* Não permitir matrículas duplicadas na mesma turma.
* Não permitir exclusão de registros que possuam vínculos ativos.
* Manter histórico de matrículas canceladas.
* Todos os registros devem possuir identificador único (ID).

## 6. Relacionamentos

* Aluno (1) → (N) Matrícula
* Turma (1) → (N) Matrícula
* Curso (1) → (N) Turma
* Professor (1) → (N) Turma
