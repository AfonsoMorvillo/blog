package br.senac.tads.dsw.blog.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.blog.persistence.entities.Publicacao;

//Permite operações de CRUD (salvar, buscar, atualizar, deletar)
//Herda métodos do JpaRepository: findAll, findById, save, delete, etc
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

	/**
	 * Método customizado para buscar todas as publicações
	 * Ordenadas pelo ID em ordem decrescente (da mais recente para a mais antiga)
	 * O Spring Data JPA cria a implementação automaticamente com base no nome do método,
	 * sim, é necessário seguir um padrão de nomenclatura.
	 * 
	 * Como funciona:
	 * - "findAll" → buscar tudo
	 * - "By" → condição (aqui não usamos nenhuma, mas poderia ser ByAutor ou ByTitulo)
	 * - "OrderByIdDesc" → ordena pelo campo "id" em ordem decrescente
	 * 
	 * OBs: se trocar "Desc" por "Asc", a ordenação seria crescente
	 */
	List<Publicacao> findAllByOrderByIdDesc();
	

	/**
	 * Exemplo de método para buscar todas as publicações de um autor específico
     * "findByAutor" → busca todas as publicações cujo campo "autor" seja igual ao parâmetro fornecido
     * JPA cria a implementação automaticamente */
	
	// List<Publicacao> findByAutor(Autor autor);

}
