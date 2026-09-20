document.getElementById("form-cadastro-usuario").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosUsuario = {
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value
    };

    const mensagemDiv = document.getElementById("mensagem-cadastro");
    mensagemDiv.textContent = "";

    try {
        const usuarioCriado = await apiRequest("/usuarios", "POST", dadosUsuario);
        mensagemDiv.textContent = `Usuário cadastrado com sucesso! ID: ${usuarioCriado.id}`;
        mensagemDiv.className = "sucesso";
        event.target.reset();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Erro ao cadastrar usuário.";
        mensagemDiv.className = "erro";
    }
});

function montarLinhaUsuario(usuario, acaoClasse, acaoLabel) {
    const statusClasse = usuario.status === "ATIVADA" ? "ativo" : "inativo";
    const statusTexto = usuario.status === "ATIVADA" ? "Ativo" : "Desativado";

    return `
        <td>${usuario.id}</td>
        <td>${usuario.email}</td>
        <td>${usuario.role}</td>
        <td><span class="status-pill ${statusClasse}">${statusTexto}</span></td>
        <td>${usuario.role === "ADMIN" ? "—" : `<button class="${acaoClasse}" data-id="${usuario.id}">${acaoLabel}</button>`}</td>
    `;
}

document.getElementById("btn-listar-ativos").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/usuarios/ativos",
        corpoTabelaId: "corpo-tabela-usuarios",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaUsuario,
        baseEndpoint: "/usuarios",
        genero: "o"
    });
});

document.getElementById("btn-listar-desativados").addEventListener("click", () => {
    listarComAcaoDeStatus({
        endpoint: "/usuarios/desativados",
        corpoTabelaId: "corpo-tabela-usuarios",
        mensagemId: "mensagem-listagem",
        montarLinha: montarLinhaUsuario,
        baseEndpoint: "/usuarios",
        genero: "o"
    });
});