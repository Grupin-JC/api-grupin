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

    public Usuario create(Usuario usuario) {
        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuario);
        usuario = usuarioOptional.orElseThrow(() -> new IllegalArgumentException("Usuário inválido"));
        return repository.save(usuario);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario get(int id) throws UsuarioNaoEncontradoException {
        Optional<Usuario> findById = repository.findById(id);
        Usuario usuario = findById
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));
        return usuario;
    }

    public Usuario update(Usuario usuario) throws UsuarioNaoEncontradoException {
        Optional<Usuario> ofNullable = Optional.ofNullable(usuario);
        usuario = ofNullable.orElseThrow(() -> new IllegalArgumentException("Membro inválido"));

        Optional<Usuario> findById = repository.findById(usuario.getId());
        Usuario usuarioAtualizado = findById
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));

        usuarioAtualizado = usuario;
        return repository.save(usuarioAtualizado);
    }

    public Usuario delete(int id) throws UsuarioNaoEncontradoException {
        Usuario usuarioDeletado = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO));
        repository.delete(usuarioDeletado);
        return usuarioDeletado;
    }
}
