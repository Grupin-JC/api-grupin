package grupin.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupin.app.exception.UsuarioNaoEncontradoException;
import grupin.app.model.Usuario;
import grupin.app.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private static final String MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado!";

    /**
     * 
     * @param usuario Objeto opcional (null ou não)
     * @return usuário salvo
     */
    public Usuario create(Usuario usuario) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuario);
        usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuário inválido"));
        return repository.save(usuario);
    }

    /**
     * 
     * @return lista de todos os usuários presentes no banco de dados
     */
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    /**
     * 
     * @param id Identificação do usuário
     * @return usuário específico
     * @throws UsuarioNaoEncontradoException Exceção lançada, caso o usuário não
     *                                       seja encontrado no banco de dados
     */
    public Usuario get(int id) throws UsuarioNaoEncontradoException {
        Optional<Usuario> findById = repository.findById(id);
        Usuario usuario = findById
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));
        return usuario;
    }

    /**
     * 
     * @param usuario Objeto opcional (null ou não)
     * @return usuário atualizado
     * @throws UsuarioNaoEncontradoException
     */
    public Usuario update(Usuario usuario) throws UsuarioNaoEncontradoException {
        Optional<Usuario> ofNullable = Optional.ofNullable(usuario);
        usuario = ofNullable.orElseThrow(() -> new IllegalArgumentException("Membro inválido"));

        Optional<Usuario> findById = repository.findById(usuario.getId());
        Usuario usuarioAtualizado = findById
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));

        usuarioAtualizado = usuario;
        return repository.save(usuarioAtualizado);
    }

    /**
     * 
     * @param id Identificação do usuário
     * @return Usuário específico
     * @throws UsuarioNaoEncontradoException Exceção lançada, caso não encontre o
     *                                       usuário no banco de dados
     */
    public Usuario delete(int id) throws UsuarioNaoEncontradoException {
        Usuario usuarioDeletado = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));
        repository.delete(usuarioDeletado);
        return usuarioDeletado;
    }
}
