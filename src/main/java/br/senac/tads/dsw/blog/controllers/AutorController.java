package br.senac.tads.dsw.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.blog.dto.AutorDTO;
import br.senac.tads.dsw.blog.persistence.entities.Autor;
import br.senac.tads.dsw.blog.services.AutorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

	private final AutorService autorService;

	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	@GetMapping
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.ok(autorService.listarTodos());
	}

	@PostMapping
	public ResponseEntity<Autor> salvar(@Valid @RequestBody AutorDTO dto) {
		Autor autor = autorService.salvar(dto);
		return ResponseEntity.ok(autor);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(autorService.buscarPorId(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		autorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
