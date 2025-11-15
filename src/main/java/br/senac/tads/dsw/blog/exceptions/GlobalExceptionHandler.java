package br.senac.tads.dsw.blog.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {

		List<String> erros = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(e -> e.getDefaultMessage())
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroDTO("Erro de validação", erros));
	}
}
