package br.senac.tads.dsw.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutorDTO {

	@NotBlank(message = "O nome do autor é obrigatório.")
	@Size(min = 10, message = "O nome do autor deve ter no mínimo 10 caracteres.")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}