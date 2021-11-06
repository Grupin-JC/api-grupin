package grupin.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupin.app.exception.CategoriaNaoEncontradaExeception;
import grupin.app.model.Categoria;
import grupin.app.repository.CategoriaRepository;

/**
 * @author Lucas-dev-back
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    private static final String MENSAGEM_ERRO_CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada!";

    /**
     * 
     * @param categoria Objeto que será salvo no banco de dados
     * @return categoria salva
     */
    public Categoria create(Categoria categoria) {
        return repository.save(categoria);
    }

    /**
     * 
     * @param id Identificação da categoria buscada
     * @return Categoria buscada
     * @throws CategoriaNaoEncontradaExeception Exceção lançada, caso o categoria
     *                                          não exista
     */
    public Categoria get(int id) throws CategoriaNaoEncontradaExeception {
        Optional<Categoria> findById = repository.findById(id);
        Categoria categoria = findById
                .orElseThrow(() -> new CategoriaNaoEncontradaExeception(MENSAGEM_ERRO_CATEGORIA_NAO_ENCONTRADA));
        return categoria;
    }

    /**
     * 
     * @return Todas as categorias presentes no banco de dados
     */
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    /**
     * 
     * @param categoria Objeto que será atualizado
     * @return Categoria atualizada
     * @throws CategoriaNaoEncontradaExeception Exceção lançada, caso não encontre a
     *                                          categoria no banco de dados
     */
    public Categoria update(Categoria categoria) throws CategoriaNaoEncontradaExeception {
        Optional<Categoria> optional = Optional.ofNullable(categoria);
        Categoria categoriaSalva = optional
                .orElseThrow(() -> new CategoriaNaoEncontradaExeception(MENSAGEM_ERRO_CATEGORIA_NAO_ENCONTRADA));
        return repository.save(categoriaSalva);
    }

    /**
     * 
     * @param id Identificação da categoria
     * @return Categoria deletada
     * @throws CategoriaNaoEncontradaExeception Exceção lançada, caso não encontre a
     *                                          categoria no banco de dados
     */
    
    public Categoria delete(int id) throws CategoriaNaoEncontradaExeception {
        Optional<Categoria> categoriaOpcional = repository.findById(id);
        Categoria categoriaDeletada = categoriaOpcional
                .orElseThrow(() -> new CategoriaNaoEncontradaExeception(MENSAGEM_ERRO_CATEGORIA_NAO_ENCONTRADA));
        repository.delete(categoriaDeletada);
        return categoriaDeletada;
    }

}
