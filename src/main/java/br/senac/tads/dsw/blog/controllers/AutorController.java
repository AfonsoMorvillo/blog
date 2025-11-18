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

/**
 * Controllers responsáveis por ter as APIs REST para Autor e Publicacao.
 * Cada método mapeia uma rota HTTP (GET, POST, PUT, DELETE) e delega para o Service.
 */
@RestController // Define que a classe é um controller REST e que retorna JSON
@RequestMapping("/api/autor") // Rota base para todos os métodos de Autor
public class AutorController {
	
	// Repare que cada metodo tem uma anotacao, ex:@GetMapping, quer dizer que e um metodo de busca de dados, etc

	private final AutorService autorService;

	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}

	/**
     * GET /api/autor
     * Lista todos os autores, vai ser usado para preencher o campo de selecao na hora de cadastrar publicacao
     */
	@GetMapping
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.ok(autorService.listarTodos());
	}

	/**
	 * Cria um novo autor a partir de um DTO validado.
	 * A anotação @Valid vai verificar todas as anotações de validação presentes na classe AutorDTO
	 * (como @NotNull, @Size, etc.) e, se houver algum problema, disparará um erro.
	 *
	 * O formato de exibição do erro foi tratado na classe GlobalExceptionHandler,
	 * para exibir corretamente no formulário
	 */
	@PostMapping
	public ResponseEntity<Autor> salvar(@Valid @RequestBody AutorDTO dto) {
		Autor autor = autorService.salvar(dto);
		return ResponseEntity.ok(autor);
	}

	/**
     * Busca um autor pelo ID, nao foi utilizado no fim
     */
	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(autorService.buscarPorId(id));
	}

	 /**
     * Exclui um autor pelo ID, tambem nao foi utilizado,pode excluir se quiser
     */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		autorService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
