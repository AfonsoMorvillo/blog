package br.senac.tads.dsw.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.senac.tads.dsw.blog.dto.AutorDTO;
import br.senac.tads.dsw.blog.persistence.entities.Autor;
import br.senac.tads.dsw.blog.persistence.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor salvar(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        return autorRepository.save(autor);
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        }
    }
}
