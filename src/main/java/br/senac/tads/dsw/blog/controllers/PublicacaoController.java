package br.senac.tads.dsw.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.senac.tads.dsw.blog.dto.PublicacaoDTO;
import br.senac.tads.dsw.blog.persistence.entities.Publicacao;
import br.senac.tads.dsw.blog.services.PublicacaoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/publicacao") // Rota base para todos os métodos de Publicacao
public class PublicacaoController {

	private final PublicacaoService publicacaoService;

	public PublicacaoController(PublicacaoService publicacaoService) {
		this.publicacaoService = publicacaoService;
	}

	 /** lista todas as publicações, em ordem da mais recente cadastrada, é usado na tela de publicacoes do blog */
	@GetMapping
	public ResponseEntity<List<Publicacao>> listar() {
		return ResponseEntity.ok(publicacaoService.listarTodas());
	}

	 /**cria uma nova publicação se o dto estiver validado com as anotacoes */
	@PostMapping
	public ResponseEntity<Publicacao> salvar(@Valid @RequestBody PublicacaoDTO dto) {
		Publicacao nova = publicacaoService.salvar(dto);
		return ResponseEntity.ok(nova);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Publicacao> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(publicacaoService.buscarPorId(id));
	}

	/** Editar uma publicação existente pelo ID */
	@PutMapping("/{id}")
	public ResponseEntity<Publicacao> atualizar(@PathVariable Long id, @Valid @RequestBody PublicacaoDTO dto) {

		Publicacao atualizada = publicacaoService.atualizar(id, dto);
		return ResponseEntity.ok(atualizada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		publicacaoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
