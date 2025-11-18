// Obtém o ID da publicação pela URL: editar-publicacao.html?id=3
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

// Carrega autores e preenche no select select
async function carregarAutores() {
    const resposta = await fetch("/api/autor");
    const autores = await resposta.json();

    const select = document.getElementById("autorId");
    autores.forEach(autor => {
        const opcaoAutor = document.createElement("option");
        opcaoAutor.value = autor.id;
        opcaoAutor.textContent = autor.nome;
        select.appendChild(opcaoAutor);
    });
}

// Carrega publicação existente para preencher o formulário
async function carregarPublicacao() {
    const resposta = await fetch(`/api/publicacao/${id}`);
    
    if (!resposta.ok) {
        alert("Erro ao carregar publicação.");
        return;
    }

    const dados = await resposta.json();

	// preenche os campos do fomrulario
    document.getElementById("titulo").value = dados.titulo;
    document.getElementById("texto").value = dados.texto;
    document.getElementById("autorId").value = dados.autor.id;
    document.getElementById("dataPublicacao").value = dados.dataPublicacao;
}

// Inicialização da página, carrega autores depois carrega a publicacao
carregarAutores().then(() => carregarPublicacao());


// -------------------- ENVIO DO FORMULÁRIO --------------------
document.getElementById("formPublicacao").addEventListener("submit", async function(e) {
    e.preventDefault();

    limparErros(); //limpa erros, supondo que estava errado e ele corrigiu, se estiver errado de novo sera marcado

	// cria o DTO para enviar
    const dados = {
        titulo: document.getElementById("titulo").value,
        texto: document.getElementById("texto").value,
        autorId: document.getElementById("autorId").value,
        dataPublicacao: document.getElementById("dataPublicacao").value
    };

    try {
        const resposta = await fetch(`/api/publicacao/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(dados)
        });

		// Deu erro 
        if (!resposta.ok) {
            const erro = await resposta.json();
            tratarErros(erro);
            return;
        }

		// Deu certo, redireciona para pagina de listagem com o sucesso de parametro 
        window.location.href = "/index.html?sucesso=2";

    } catch (error) {
        console.error("Erro ao editar:", error);
    }
});

// -------------------- FUNÇÕES DE VALIDAÇÃO --------------------
// limpa erro de todos os campos
function limparErros() {
    ["titulo", "texto", "autorId", "dataPublicacao"].forEach(campo => {
        const input = document.getElementById(campo);
        const erro = document.getElementById("erro-" + campo);

        input.classList.remove("is-invalid");
        erro.textContent = "";
    });
}

// Para o obj de erros que o back envia, percorre o mapa que tem a chave do campo e adiciona o erro no campo
function tratarErros(erro) {
    const mapa = erro.detalhes;

    Object.keys(mapa).forEach(campo => {
        adicionarErroEmCampo(campo, mapa[campo]);
    });
}

// adiciona a classe do boostrap que deixa vermelho e adiciona as mensagens de erro, ex: tamanho deve ser 10
function adicionarErroEmCampo(campo, mensagens) {
    const input = document.getElementById(campo);
    const erroDiv = document.getElementById("erro-" + campo);

    input.classList.add("is-invalid");
    erroDiv.textContent = mensagens.join(" ");
}
