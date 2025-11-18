package br.senac.tads.dsw.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para Autor.
 * É enviado ao alterar ou cadastrar autor/publicacao pelo formulario no HTML
 * Serve para transferir dados
 * Não é persistido no banco, apenas transporta dados.
 */
public class AutorDTO {

	@NotBlank(message = "O nome do autor é obrigatório.") // Garante que o campo não seja nulo, vazio ou apenas espaços
	@Size(min = 10, message = "O nome do autor deve ter no mínimo 10 caracteres.") // Garante que o nome tenha pelo menos 10 caracteres
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}