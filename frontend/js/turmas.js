document.getElementById("form-cadastro-turma").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosTurma = {
        nome: document.getElementById("nome").value,
        idProfessor: Number(document.getElementById("idProfessor").value),
        idCurso: Number(document.getElementById("idCurso").value)
    };

    const mensagemDiv = document.getElementById("mensagem-cadastro");
    mensagemDiv.textContent = "";

    try {
        const turmaCriada = await apiRequest("/turmas", "POST", dadosTurma);
        mensagemDiv.textContent = `Turma cadastrada com sucesso! ID: ${turmaCriada.id}`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao cadastrar turma.";
        mensagemDiv.className = "erro";
    }
});

function montarLinhaTurma(turma, acaoClasse, acaoLabel) {
    const statusClasse = turma.status === "ATIVADA" ? "ativo" : "inativo";
    const statusTexto = turma.status === "ATIVADA" ? "Ativa" : "Desativada";

    return `
        <td>${turma.id}</td>
        <td>${turma.nome}</td>
        <td>${turma.data}</td>
        <td>${turma.nomeProfessor}</td>
        <td>${turma.nomeCurso}</td>
        <td><span class="status-pill ${statusClasse}">${statusTexto}</span></td>
        <td><button class="${acaoClasse}" data-id="${turma.id}">${acaoLabel}</button></td>
    `;
}

document.getElementById("btn-listar-ativas").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/turmas/ativadas",
        corpoTabelaId: "corpo-tabela-turmas",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaTurma,
        baseEndpoint: "/turmas",
        genero: "a"
    });
});

document.getElementById("btn-listar-desativadas").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/turmas/desativadas",
        corpoTabelaId: "corpo-tabela-turmas",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaTurma,
        baseEndpoint: "/turmas",
        genero: "a"
    });
});