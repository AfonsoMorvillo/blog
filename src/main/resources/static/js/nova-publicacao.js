// Carrega autores ao abrir a página
async function carregarAutores() {
	const resposta = await fetch("/api/autor");
	const autores = await resposta.json();

	const select = document.getElementById("autor");
	autores.forEach(autor => {
		const opcaoAutor = document.createElement("option");
		opcaoAutor.value = autor.id;
		opcaoAutor.textContent = autor.nome;
		select.appendChild(opcaoAutor);
	});
}

carregarAutores();

// Salvar publicação
document.getElementById("formPublicacao").addEventListener("submit", async (event) => {
	event.preventDefault();

	const dto = {
		titulo: document.getElementById("titulo").value,
		texto: document.getElementById("texto").value,
		autorId: document.getElementById("autor").value,
		dataPublicacao: document.getElementById("dataPublicacao").value
	};

	const resposta = await fetch("/api/publicacao", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(dto)
	});

	if (resposta.ok) {
		alert("Publicação salva com sucesso!");
		document.getElementById("formPublicacao").reset();
	} else {
		const erro = await resposta.json();
		alert("Erro ao salvar: " + erro.detalhes?.join(", "));
	}
});
