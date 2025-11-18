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

    /**
     * Para salvar, transforma o AutorDTO que tem as validacoes na classe Autor que é a que reflete o banco de dados, foi criada duas classes para 
     * nao expor a classe que representa seu banco de dados, mantendo as validacoes no DTO
     */
    public Autor salvar(AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        return autorRepository.save(autor);
    }

    /**
     * OBS: No projeto atual este método não está sendo utilizado
     */
    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    /**
     * OBS: No projeto atual este método não está sendo utilizado
     */
    public void excluir(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        }
    }
}
