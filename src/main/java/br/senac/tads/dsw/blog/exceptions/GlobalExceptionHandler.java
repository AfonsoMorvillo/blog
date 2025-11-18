package br.senac.tads.dsw.blog.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Trata globalmente erros de validação (@Valid) anotado nos DTOs.
 * Sempre que uma validação falhar, este handler será chamado e disparado para a requisicao.
 */

@ControllerAdvice // Indica que a classe vai interceptar exceções em todos os controllers
public class GlobalExceptionHandler {

	  /**
     * Captura exceções do tipo MethodArgumentNotValidException,
     * que ocorrem quando um DTO não passa nas validações anotadas.
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

		Map<String, List<String>> errosPorCampo = new HashMap<>();

		// Percorre cada erro de validação encontrado no DTO
		for (FieldError erro : ex.getBindingResult().getFieldErrors()) {

			String campo = erro.getField(); // Nome do campo com erro
			String mensagem = erro.getDefaultMessage(); // Mensagem de validação

			// Se o campo ainda não existe no mapa, cria uma nova lista para ele
			if (!errosPorCampo.containsKey(campo)) {
				errosPorCampo.put(campo, new ArrayList<>());
			}

			// Adiciona a mensagem de erro na lista correspondente ao campo
			errosPorCampo.get(campo).add(mensagem);
		}

		 // Cria um DTO de erro para retornar para a requisicao
		ErroDTO erro = new ErroDTO("Erros de validação", errosPorCampo);

		// Retorna 400 Bad Request com o DTO de erros
		return ResponseEntity.badRequest().body(erro);
	}
}
