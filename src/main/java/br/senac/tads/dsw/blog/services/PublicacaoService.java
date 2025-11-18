package br.senac.tads.dsw.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.tads.dsw.blog.dto.PublicacaoDTO;
import br.senac.tads.dsw.blog.persistence.entities.Autor;
import br.senac.tads.dsw.blog.persistence.entities.Publicacao;
import br.senac.tads.dsw.blog.persistence.repository.AutorRepository;
import br.senac.tads.dsw.blog.persistence.repository.PublicacaoRepository;

@Service
public class PublicacaoService {

	private final PublicacaoRepository publicacaoRepository;
	private final AutorRepository autorRepository;

	public PublicacaoService(PublicacaoRepository publicacaoRepository, AutorRepository autorRepository) {
		this.publicacaoRepository = publicacaoRepository;
		this.autorRepository = autorRepository;
	}

	/**
     * Lista todas as publicações ordenadas do mais recente para o mais antigo, e chamado pelo controller que envia para o html
     */
	public List<Publicacao> listarTodas() {
	    return publicacaoRepository.findAllByOrderByIdDesc();
	}


	 /**
     * Salva uma nova publicação a partir de um DTO com as validacoes, igual em autor
     */
	public Publicacao salvar(PublicacaoDTO dto) {

		// Busca o autor pelo ID passado no DTO, o autor foi selecionado no html e como so temos o codigo precisamos busacr ele para relacionar 
		// Eu preciso do objeto todo, so o ID nao funciona
		Autor autor = autorRepository.findById(dto.getAutorId()).orElse(null);

		Publicacao p = new Publicacao();
		p.setTitulo(dto.getTitulo());
		p.setTexto(dto.getTexto());
		p.setDataPublicacao(dto.getDataPublicacao());
		p.setAutor(autor);

		return publicacaoRepository.save(p);
	}

	public Publicacao buscarPorId(Long id) {
		return publicacaoRepository.findById(id).orElse(null);
	}

	
	 /**
     * Atualiza uma publicação existente a partir do ID e DTO
     * pega o existente no banco e seta os valores que estao vindo novos
     */
	public Publicacao atualizar(Long id, PublicacaoDTO dto) {

		Publicacao existente = buscarPorId(id);

		if (existente == null) {
			return null;
		}

		Autor autor = autorRepository.findById(dto.getAutorId()).orElse(null);

		existente.setTitulo(dto.getTitulo());
		existente.setTexto(dto.getTexto());
		existente.setDataPublicacao(dto.getDataPublicacao());
		existente.setAutor(autor);

		return publicacaoRepository.save(existente);
	}

	public void excluir(Long id) {
		if (publicacaoRepository.existsById(id)) {
			publicacaoRepository.deleteById(id);
		}
	}
}
