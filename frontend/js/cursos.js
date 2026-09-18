document.getElementById("form-cadastro-curso").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosCurso = {
        nome: document.getElementById("nome").value,
        descricao: document.getElementById("descricao").value,
        cargaHoraria: Number(document.getElementById("cargaHoraria").value)
    };

    const mensagemDiv = document.getElementById("mensagem-cadastro");
    mensagemDiv.textContent = "";

    try {
        const cursoCriado = await apiRequest("/cursos", "POST", dadosCurso);
        mensagemDiv.textContent = `Curso cadastrado com sucesso! ID: ${cursoCriado.id}`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao cadastrar curso.";
        mensagemDiv.className = "erro";
    }
});

function montarLinhaCurso(curso, acaoClasse, acaoLabel) {
    const statusClasse = curso.status === "ATIVADA" ? "ativo" : "inativo";
    const statusTexto = curso.status === "ATIVADA" ? "Ativo" : "Desativado";

    return `
        <td>${curso.id}</td>
        <td>${curso.nome}</td>
        <td>${curso.descricao}</td>
        <td>${curso.cargaHoraria}</td>
        <td><span class="status-pill ${statusClasse}">${statusTexto}</span></td>
        <td><button class="${acaoClasse}" data-id="${curso.id}">${acaoLabel}</button></td>
    `;
}

document.getElementById("btn-listar-ativos").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/cursos/ativados",
        corpoTabelaId: "corpo-tabela-cursos",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaCurso,
        baseEndpoint: "/cursos",
        genero: "o"
    });
});

document.getElementById("btn-listar-desativados").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/cursos/desativados",
        corpoTabelaId: "corpo-tabela-cursos",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaCurso,
        baseEndpoint: "/cursos",
        genero: "o"
    });
});