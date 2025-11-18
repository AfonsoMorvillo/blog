// Salvar autor
document.getElementById("formAutor").addEventListener("submit", async function(e) {
    e.preventDefault();

    limparErros();

    const dados = {
        nome: document.getElementById("nome").value
    };

    try {
        const resposta = await fetch("/api/autor", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados)
        });

        if (!resposta.ok) {
            const erro = await resposta.json();
            tratarErros(erro);
            return;
        }

        // sucesso
        window.location.href = "/index.html?sucesso=3";

    } catch (error) {
        console.error("Erro ao salvar autor:", error);
    }
});


function limparErros() {
    const campo = document.getElementById("nome");
    const erro = document.getElementById("erro-nome");

    campo.classList.remove("is-invalid");
    erro.textContent = "";
}

function tratarErros(erro) {
    if (!erro?.detalhes) return;

    const mensagens = erro.detalhes.nome;

    const campo = document.getElementById("nome");
    const erroDiv = document.getElementById("erro-nome");

    campo.classList.add("is-invalid");
    erroDiv.textContent = mensagens.join(" ");
}
