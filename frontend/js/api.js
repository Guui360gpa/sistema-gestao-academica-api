const API_BASE_URL = "http://localhost:8080";

async function apiRequest(endpoint, method = "GET", body = null) {
    const token = localStorage.getItem("token");

    const options = {
        method,
        headers: {
            "Content-Type": "application/json"
        }
    };

    if (token) {
        options.headers["Authorization"] = `Bearer ${token}`;
    }

    if (body !== null) {
        options.body = JSON.stringify(body);
    }

    const response = await fetch(`${API_BASE_URL}${endpoint}`, options);

    if (response.status === 401) {
        localStorage.clear();
        window.location.href = "login.html";
        return;
    }

    let data = null;
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
        data = await response.json();
    }

    if (!response.ok) {
        throw {
            status: response.status,
            body: data
        };
    }

    return data;
}

async function listarComAcaoDeStatus({
    endpoint,
    corpoTabelaId,
    mensagemId,
    montarLinha,
    baseEndpoint,
    genero = "o"
}) {
    const corpoTabela = document.getElementById(corpoTabelaId);
    const mensagemDiv = document.getElementById(mensagemId);

    corpoTabela.innerHTML = "";
    mensagemDiv.textContent = "";

    try {
        const itens = await apiRequest(endpoint, "GET");

        itens.forEach((item) => {
            const estaAtivo = item.status === "ATIVADA";
            const acaoLabel = estaAtivo ? "Desativar" : "Ativar";
            const acaoClasse = estaAtivo ? "btn-desativar" : "btn-ativar";

            const linha = document.createElement("tr");
            linha.innerHTML = montarLinha(item, acaoClasse, acaoLabel);
            corpoTabela.appendChild(linha);
        });

        corpoTabela.querySelectorAll(".btn-desativar").forEach((botao) => {
            botao.addEventListener("click", () =>
                alternarStatusGenerico(botao.dataset.id, "desativar", endpoint, baseEndpoint, mensagemId, genero, () =>
                    listarComAcaoDeStatus({ endpoint, corpoTabelaId, mensagemId, montarLinha, baseEndpoint, genero })
                )
            );
        });

        corpoTabela.querySelectorAll(".btn-ativar").forEach((botao) => {
            botao.addEventListener("click", () =>
                alternarStatusGenerico(botao.dataset.id, "ativar", endpoint, baseEndpoint, mensagemId, genero, () =>
                    listarComAcaoDeStatus({ endpoint, corpoTabelaId, mensagemId, montarLinha, baseEndpoint, genero })
                )
            );
        });
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Nenhum item encontrado.";
        mensagemDiv.className = "erro";
    }
}

async function alternarStatusGenerico(id, acao, endpointOrigem, baseEndpoint, mensagemId, genero, recarregar) {
    const mensagemDiv = document.getElementById(mensagemId);

    try {
        await apiRequest(`${baseEndpoint}/${id}/${acao}`, "PATCH");
        const acaoLabel = acao === "ativar" ? "ativad" : "desativad";
        mensagemDiv.textContent = `Item ${acaoLabel}${genero} com sucesso.`;
        mensagemDiv.className = "sucesso";
        recarregar();
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || `Erro ao ${acao} item.`;
        mensagemDiv.className = "erro";
    }
}