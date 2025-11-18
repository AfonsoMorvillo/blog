package br.senac.tads.dsw.blog.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Indica que a classe é uma entidade JPA (vira uma tabela no banco)
@Table(name = "autor") // Nome da tabela
public class Autor {
	// No banco de dados, isso será equivalente a:
    // CREATE TABLE autor (
    //     id BIGINT AUTO_INCREMENT PRIMARY KEY,
    //     nome VARCHAR(100) NOT NULL
    // );

	@Id // Define o campo como chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // O banco gera o ID automaticamente (ordem crescente)
	private Long id;

	@Column(nullable = false, length = 100) // não pode ser null e tem no máximo 100 caracteres, isso no banco de dados
	private String nome;
	

	// Getter e Setters padrao
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
