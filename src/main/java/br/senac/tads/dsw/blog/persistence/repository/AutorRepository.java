package br.senac.tads.dsw.blog.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.blog.persistence.entities.Autor;

//Permite operações de CRUD (salvar, buscar, atualizar, deletar)
//Herda métodos do JpaRepository: findAll, findById, save, delete, etc
public interface AutorRepository extends JpaRepository<Autor, Long> {}
