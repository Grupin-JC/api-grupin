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

import grupin.app.exception.MembroNaoEncontradoException;
import grupin.app.model.Membro;
import grupin.app.model.enums.StatusMembro;
import grupin.app.repository.MembroRepository;

/**
 * @author Lucas-dev-back
 */
@Service
public class MembroService {

    @Autowired
    private MembroRepository repository;

    private static final String MENSSAGEM_ERRO_MEMBRO_NAO_ENCONTRADO = "Esse membro não existe!";
    private static final String CAMINHO_IMAGEM = "/img/group/";

    /**
     * 
     * @param id Identificação do Membro
     * @return Um membro específico do banco de dados
     * @throws MembroNaoEncontradoException Exceção para caso o id seja inválido
     */
    public Membro get(int id) throws MembroNaoEncontradoException {
        Optional<Membro> membroOptional = repository.findById(id);
        return membroOptional.orElseThrow(() -> new MembroNaoEncontradoException(MENSSAGEM_ERRO_MEMBRO_NAO_ENCONTRADO));
    }

    /**
     * 
     * @return Todos os membros do banco de dados
     */
    public List<Membro> findAll() {
        return repository.findAll();
    }

    /**
     * 
     * @param membro  Membro opcional (null ou não)
     * @param arquivo Arquivo opcional (null ou não)
     * @return membro salvo
     * @throws IOException              Exceção para caso o arquivo esteja
     *                                  corrompido ao capturar os bytes do arquivo
     * @throws IllegalArgumentException Exceção para caso o arquivo não esteja
     *                                  presente
     */
    public Membro create(Membro membro, MultipartFile arquivo) throws IOException {
        Membro membroSalvo = membro;
        Optional<MultipartFile> ofNullable = Optional.ofNullable(arquivo);

        arquivo = ofNullable.orElseThrow(() -> new IllegalArgumentException("Arquivo inválido ou não está presente"));
        byte[] bytes = arquivo.getBytes();
        Path caminho = Paths.get(CAMINHO_IMAGEM + membroSalvo + arquivo.getOriginalFilename());
        Files.write(caminho, bytes);
        membroSalvo.setFotoSrc(membroSalvo.getId() + arquivo.getOriginalFilename());

        membroSalvo = repository.saveAndFlush(membroSalvo);

        return membroSalvo;
    }

    /**
     * 
     * @param membro Membro opcional (null ou não)
     * @return Objeto atualizado no banco
     * @throws MembroNaoEncontradoException Exceção para caso o membro não seja
     *                                      encontrado no banco de dados
     */
    public Membro update(Membro membro) throws MembroNaoEncontradoException {
        Membro membroQueSeraAtualizado = checkParam(membro);
        Optional<Membro> membroVindoDoBanco = repository.findById(membroQueSeraAtualizado.getId());
        membroQueSeraAtualizado = membroVindoDoBanco
                .orElseThrow(() -> new MembroNaoEncontradoException(MENSSAGEM_ERRO_MEMBRO_NAO_ENCONTRADO));
        return repository.saveAndFlush(membroQueSeraAtualizado);
    }

    /**
     * 
     * @param membro Membro opcional (nulo ou não)
     * @return membro passado na verificação
     * @throws IllegalArgumentException Execeção lançada, caso o membro passado seja
     *                                  null
     */
    private Membro checkParam(Membro membro) {
        Optional<Membro> membroOptional = Optional.ofNullable(membro);
        return membroOptional.orElseThrow(() -> new IllegalArgumentException("Membro não informado"));
    }

    /**
     * 
     * @param id Identificação do Membro
     * @return Objeto salvo no banco
     * @throws MembroNaoEncontradoException Exceção para caso o membro não seja
     *                                      encontrado no banco de dados
     */
    public Membro delete(int id) throws MembroNaoEncontradoException {
        var membroBuscadoPorId = Optional.ofNullable(repository.findById(id))
                .orElseThrow(() -> new MembroNaoEncontradoException(MENSSAGEM_ERRO_MEMBRO_NAO_ENCONTRADO));

        Membro membro = membroBuscadoPorId.orElse(new Membro());
        membro.setStatus(StatusMembro.INATIVO);

        return repository.saveAndFlush(membro);
    }

}