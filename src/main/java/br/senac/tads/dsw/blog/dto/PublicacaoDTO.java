package br.senac.tads.dsw.blog.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PublicacaoDTO {

	@NotBlank(message = "O título é obrigatório.")
	@Size(min = 10, message = "O título deve ter no mínimo 10 caracteres.")
	private String titulo;

	@NotBlank(message = "O texto é obrigatório.")
	@Size(min = 10, message = "O texto deve ter no mínimo 10 caracteres.")
	private String texto;

	@NotNull(message = "O autor é obrigatório.")
	// Garante que o autorId não seja nulo
	private Long autorId;

	@NotNull(message = "A data de publicação é obrigatória.")
	private LocalDate dataPublicacao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Long getAutorId() {
		return autorId;
	}

	public void setAutorId(Long autorId) {
		this.autorId = autorId;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

}
