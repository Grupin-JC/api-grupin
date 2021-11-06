package grupin.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import grupin.app.exception.ProjetoNaoEncontradoExeception;
import grupin.app.model.Projeto;
import grupin.app.repository.ProjetoRepository;

/**
 * 
 * @author Lucas-dev-back
 */
@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    private static final String MENSAGEM_ERRO_PROJETO_NAO_ENCONTRADO = "Projeto não encontrado";
    private static final String CAMINHO_IMAGEM = "/img/games/";

    public Projeto create(Projeto projeto, MultipartFile arquivo) throws IOException {
        Projeto projetoSalvo = repository.save(projeto);
        Optional<MultipartFile> ofNullable = Optional.ofNullable(arquivo);

        arquivo = ofNullable.orElseThrow(() -> new IllegalArgumentException("Arquivo inválido ou não está presente"));
        byte[] bytes = arquivo.getBytes();
        Path caminho = Paths.get(CAMINHO_IMAGEM + projetoSalvo.getId() + arquivo.getOriginalFilename());
        Files.write(caminho, bytes);
        projetoSalvo.setFotoSrc(projetoSalvo.getId() + arquivo.getOriginalFilename());

        return repository.saveAndFlush(projetoSalvo);
    }

    public Projeto get(int id) throws ProjetoNaoEncontradoExeception {
        Optional<Projeto> projetoOpcional = repository.findById(id);
        Projeto projetoVindoDoBanco = projetoOpcional
                .orElseThrow(() -> new ProjetoNaoEncontradoExeception(MENSAGEM_ERRO_PROJETO_NAO_ENCONTRADO));
        return projetoVindoDoBanco;
    }

    public List<Projeto> findAll() {
        return repository.findAll();
    }

    public Projeto update(Projeto projeto) throws ProjetoNaoEncontradoExeception {
        Optional<Projeto> projetoOpcional = Optional.ofNullable(projeto);
        projeto = projetoOpcional.orElseThrow(() -> new IllegalArgumentException("O projeto informado está vazio"));

        Optional<Projeto> buscadoNoBanco = repository.findById(projeto.getId());
        Projeto projetoAtualizado = buscadoNoBanco
                .orElseThrow(() -> new ProjetoNaoEncontradoExeception(MENSAGEM_ERRO_PROJETO_NAO_ENCONTRADO));

        projetoAtualizado = projeto;
        return repository.saveAndFlush(projetoAtualizado);
    }

    public Projeto delete(int id) throws ProjetoNaoEncontradoExeception {
        Optional<Projeto> projetoOpcional = repository.findById(id);
        Projeto projeto = projetoOpcional
                .orElseThrow(() -> new ProjetoNaoEncontradoExeception(MENSAGEM_ERRO_PROJETO_NAO_ENCONTRADO));
        repository.delete(projeto);
        return projeto;
    }

}
