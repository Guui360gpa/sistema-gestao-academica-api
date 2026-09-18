document.getElementById("form-cadastro-professor").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosProfessor = {
        nome: document.getElementById("nome").value,
        especialidade: document.getElementById("especialidade").value.toUpperCase(),
        email: document.getElementById("email").value,
        telefone: document.getElementById("telefone").value
    };

    const mensagemDiv = document.getElementById("mensagem-cadastro");
    mensagemDiv.textContent = "";

    try {
        const professorCriado = await apiRequest("/professores", "POST", dadosProfessor);
        mensagemDiv.textContent = `Professor cadastrado com sucesso! ID: ${professorCriado.id}`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao cadastrar professor.";
        mensagemDiv.className = "erro";
    }
});

function montarLinhaProfessor(professor, acaoClasse, acaoLabel) {
    const statusClasse = professor.status === "ATIVADA" ? "ativo" : "inativo";
    const statusTexto = professor.status === "ATIVADA" ? "Ativo" : "Desativado";

    return `
        <td>${professor.id}</td>
        <td>${professor.nome}</td>
        <td>${professor.especialidade}</td>
        <td>${professor.email}</td>
        <td>${professor.telefone}</td>
        <td><span class="status-pill ${statusClasse}">${statusTexto}</span></td>
        <td><button class="${acaoClasse}" data-id="${professor.id}">${acaoLabel}</button></td>
    `;
}

document.getElementById("btn-listar-ativos").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/professores/ativados",
        corpoTabelaId: "corpo-tabela-professores",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaProfessor,
        baseEndpoint: "/professores",
        genero: "o"
    });
});

document.getElementById("btn-listar-desativados").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/professores/desativados",
        corpoTabelaId: "corpo-tabela-professores",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaProfessor,
        baseEndpoint: "/professores",
        genero: "o"
    });
});