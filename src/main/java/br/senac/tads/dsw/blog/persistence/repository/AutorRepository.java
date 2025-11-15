package br.senac.tads.dsw.blog.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.blog.persistence.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {}
