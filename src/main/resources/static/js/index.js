// ====================== ALERTA DE SUCESSO ======================

/**
 * Mostra um alerta verde no canto da tela.
 * O texto aparece por 3 segundos e depois some sozinho.
 */
function mostrarAlerta(mensagem) {
	const alerta = document.getElementById("alertaSucesso");
	alerta.textContent = mensagem;
	alerta.style.display = "block";

	setTimeout(() => {
		alerta.style.display = "none";
	}, 3000);
}

/**
 * Verifica se a URL possui o parâmetro ?sucesso
 * Cada número representa um tipo de mensagem:
 * 1 = inclusão de publicação
 * 2 = alteração
 * 3 = inclusão de autor
 *
 * Depois de mostrar o alerta, o parâmetro é removido da URL
 * para evitar exibir o alerta novamente ao atualizar a página.
 */
function verificarAlertaURL() {
	const params = new URLSearchParams(window.location.search);

	if (params.has("sucesso")) {
		const tipo = params.get("sucesso");

		if (tipo === "1") {
			mostrarAlerta("Publicação incluída com sucesso!");
		} else if (tipo === "2") {
			mostrarAlerta("Publicação alterada com sucesso!");
		} else if (tipo === "3") {
			mostrarAlerta("Autor incluído com sucesso!");
		}

		// Remove o parâmetro sem recarregar a página
		params.delete("sucesso");
		const novaURL = window.location.pathname +
			(params.toString() ? `?${params.toString()}` : "");
		window.history.replaceState({}, "", novaURL);
	}
}

verificarAlertaURL();


// ====================== LISTAGEM DE PUBLICAÇÕES ======================

/**
 * Busca todas as publicações na API e monta os cards na tela.
 * Também verifica se a publicação ainda não foi publicada (data maior)
 */
async function carregarPublicacoes() {
	const resposta = await fetch("/api/publicacao");
	const publicacoes = await resposta.json();

	const lista = document.getElementById("listaPublicacoes");
	lista.innerHTML = ""; // limpa antes de carregar

	publicacoes.forEach(pub => {

		const div = document.createElement("div");
		div.classList.add("card-publicacao");

		const dataHoje = new Date().toISOString().split("T")[0];
		const naoPublicado = pub.dataPublicacao > dataHoje;

		div.innerHTML = `
            <h4>${pub.titulo}</h4>

            <p><strong>Autor:</strong> ${pub.autor.nome}</p>

            <p>
                <strong>Publicado em:</strong> ${formatarData(pub.dataPublicacao)}
				${naoPublicado ? `<span class="badge text-bg-warning">NÃO PUBLICADO</span>` : ""}
            </p>

            <p>${pub.texto}</p>

            <div class="mt-3">
                <a href="editar-publicacao.html?id=${pub.id}" class="btn btn-primary btn-sm me-2">
                    Alterar
                </a>
                <button type="button" class="btn btn-danger btn-sm" onclick="confirmarExclusao(${pub.id})">
                    Excluir
                </button>
            </div>
        `;

		lista.appendChild(div);
	});
}


/**
 * Recebe uma data no formato yyyy-MM-dd
 * e converte para dd/MM/yyyy.
 */
function formatarData(dataISO) {
	const partes = dataISO.split("-");
	return `${partes[2]}/${partes[1]}/${partes[0]}`;
}


// ====================== EXCLUSÃO COM MODAL BOOTSTRAP ======================

// Armazena ID temporário a ser excluído
let idParaExcluir = null;

/**
 * Abre a modal de confirmação de exclusão.
 * Também altera o texto exibido na janela.
 */
function confirmarExclusao(id) {
	idParaExcluir = id;

	// Alterar texto exibido na modal
	document.getElementById("textoConfirmacao").textContent =
		`Deseja excluir a publicação ID ${id}?`;

	// Abrir modal
	const modal = new bootstrap.Modal(document.getElementById("modalConfirmarExclusao"));
	modal.show();
}

/**
 * Evento do botão "SIM" dentro da modal.
 * Se o usuário confirmar, envia DELETE para a API.
 * Após excluir, recarrega a listagem e mostra o alerta.
 */
document.getElementById("btnConfirmarSim").addEventListener("click", async function() {

	if (!idParaExcluir) return;

	const resp = await fetch(`/api/publicacao/${idParaExcluir}`, {
		method: "DELETE"
	});

	if (resp.ok) {
		mostrarAlerta("Publicação excluída com sucesso!");
		carregarPublicacoes();
	} else {
		alert("Erro ao excluir.");
	}

	// Fechar modal
	bootstrap.Modal.getInstance(
		document.getElementById("modalConfirmarExclusao")
	).hide();

	idParaExcluir = null;
});


// ====================== INICIALIZAÇÃO ======================

/**
 * Ao abrir a página:
 * - Carrega as publicações
 * - E verifica se há alerta via URL
 */
carregarPublicacoes();
