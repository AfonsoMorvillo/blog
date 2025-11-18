package br.senac.tads.dsw.blog.exceptions;

import java.util.List;
import java.util.Map;

/**
 * DTO usado para retornar erros de validação para o html.
 * Contém:
 * - mensagem geral
 * - detalhes dos campos com suas mensagens de erro para colocar vermelho e deixar em baixo do campo certo
 */
public class ErroDTO {

	private String mensagem;
	private Map<String, List<String>> campoDetalhes; // tem o nome do campo que esta com erro e seus erros
													// ex: campoNome ; tamanho tem que ser 10...

	public ErroDTO(String mensagem, Map<String, List<String>> campoDetalhes) {
		this.mensagem = mensagem;
		this.campoDetalhes = campoDetalhes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Map<String, List<String>> getDetalhes() {
		return campoDetalhes;
	}

}
