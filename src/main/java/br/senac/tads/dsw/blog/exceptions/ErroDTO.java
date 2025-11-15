package br.senac.tads.dsw.blog.exceptions;

import java.util.List;

public class ErroDTO {

	private String mensagem;
	private List<String> detalhes;

	public ErroDTO(String mensagem, List<String> detalhes) {
		this.mensagem = mensagem;
		this.detalhes = detalhes;
	}

	public String getMensagem() {
		return mensagem;
	}

	public List<String> getDetalhes() {
		return detalhes;
	}

}
