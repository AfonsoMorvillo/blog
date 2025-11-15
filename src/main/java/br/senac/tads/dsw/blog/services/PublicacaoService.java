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

	public List<Publicacao> listarTodas() {
		return publicacaoRepository.findAll();
	}

	public Publicacao salvar(PublicacaoDTO dto) {

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
