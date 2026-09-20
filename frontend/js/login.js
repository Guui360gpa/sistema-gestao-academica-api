document.getElementById("form-login").addEventListener("submit", async (event) => {
    event.preventDefault();

    const dadosLogin = {
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value
    };

    const mensagemDiv = document.getElementById("mensagem-login");
    mensagemDiv.textContent = "";

    try {
        const resposta = await apiRequest("/login", "POST", dadosLogin);

        localStorage.setItem("token", resposta.token);
        localStorage.setItem("email", resposta.email);
        localStorage.setItem("role", resposta.role);

        window.location.href = "index.html";
    } catch (erro) {
        mensagemDiv.textContent = erro.body?.mensagem || "Email ou senha inválidos.";
        mensagemDiv.className = "erro";
    }
});