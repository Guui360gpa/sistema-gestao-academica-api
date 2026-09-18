document.getElementById("form-cadastro-aluno").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosAluno = {
        nome: document.getElementById("nome").value,
        dataNascimento: document.getElementById("dataNascimento").value,
        email: document.getElementById("email").value
    };

    const mensagemDiv = document.getElementById("mensagem-cadastro");
    mensagemDiv.textContent = "";

    try {
        const alunoCriado = await apiRequest("/alunos", "POST", dadosAluno);
        mensagemDiv.textContent = `Aluno cadastrado com sucesso! RA: ${alunoCriado.ra}`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao cadastrar aluno.";
        mensagemDiv.className = "erro";
    }
});

document.getElementById("btn-buscar-turma").addEventListener("click", async () => {
    const turmaId = document.getElementById("turmaId").value;
    const corpoTabela = document.getElementById("corpo-tabela-alunos");
    const mensagemDiv = document.getElementById("mensagem-listagem");

    corpoTabela.innerHTML = "";
    mensagemDiv.textContent = "";

    if (!turmaId) {
        mensagemDiv.textContent = "Informe o ID da turma.";
        mensagemDiv.className = "erro";
        return;
    }

    try {
        const alunos = await apiRequest(`/alunos/turma/${turmaId}`, "GET");

        alunos.forEach((aluno) => {
            const linha = document.createElement("tr");
            linha.innerHTML = `
                <td>${aluno.ra}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.idade}</td>
            `;
            corpoTabela.appendChild(linha);
        });
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao buscar alunos.";
        mensagemDiv.className = "erro";
    }
});

document.getElementById("form-matricula").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosMatricula = {
        alunoRa: Number(document.getElementById("matriculaAlunoRa").value),
        turmaId: Number(document.getElementById("matriculaTurmaId").value)
    };

    const mensagemDiv = document.getElementById("mensagem-matricula");
    mensagemDiv.textContent = "";

    try {
        const matriculaCriada = await apiRequest("/alunos/matricula", "POST", dadosMatricula);
        mensagemDiv.textContent = `Matrícula realizada! ${matriculaCriada.nomeAluno} na turma ${matriculaCriada.nomeTurma}.`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao matricular aluno.";
        mensagemDiv.className = "erro";
    }
});