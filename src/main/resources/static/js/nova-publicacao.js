// Carrega autores ao abrir a página
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

carregarAutores();

// Salvar publicação
document.getElementById("formPublicacao").addEventListener("submit", async function(e) {
	e.preventDefault();

	limparErros();

	const dados = {
		titulo: document.getElementById("titulo").value,
		texto: document.getElementById("texto").value,
		autorId: document.getElementById("autorId").value,
		dataPublicacao: document.getElementById("dataPublicacao").value
	};

	try {
		const resposta = await fetch("/api/publicacao", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(dados)
		});

		if (!resposta.ok) {
			const erro = await resposta.json();
			tratarErros(erro);
			return;
		}

		window.location.href = "/index.html?sucesso=1";

	} catch (error) {
		console.error("Erro ao salvar:", error);
	}
});

function limparErros() {
	["titulo", "texto", "autorId", "dataPublicacao"].forEach(campo => {
		const input = document.getElementById(campo);
		const erro = document.getElementById("erro-" + campo);

		input.classList.remove("is-invalid");
		erro.textContent = "";
	});
}

function tratarErros(erro) {
	const mapa = erro.detalhes;

	Object.keys(mapa).forEach(campo => {
		adicionarErroEmCampo(campo, mapa[campo]);
	});
}

function adicionarErroEmCampo(campo, mensagens) {
	const input = document.getElementById(campo);
	const erroDiv = document.getElementById("erro-" + campo);

	input.classList.add("is-invalid");
	erroDiv.textContent = mensagens.join(" ");
}
