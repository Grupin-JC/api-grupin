package grupin.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grupin.app.exception.UsuarioNaoEncontradoException;
import grupin.app.model.Usuario;
import grupin.app.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Lucas-dev-back
 */
@RestController
@RequestMapping(value = "/api/usuarios")
@Tag(name = "Usuário")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    private static final String MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado!";

    /**
     * 
     * @param usuario é um objeto json
     * @return o objeto salvo no BD
     */
    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "Salva um usuário no banco de dados", responses = {
            @ApiResponse(description = "Salvo com sucesso", responseCode = "201", content = @Content(mediaType = "application/json")) })
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(usuario));
    }

    /**
     * 
     * @return lista de todos os usuários que estão no banco
     */
    @ResponseBody
    @GetMapping
    @Operation(summary = "Busca todos os usuários do banco de dados", responses = {
            @ApiResponse(description = "lista de usuários buscada com sucesso", responseCode = "200") })
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * 
     * @param id identificação do usuário
     * @return objeto baseado no id
     */
    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Retorna um usuário específico do banco de dados", responses = {
            @ApiResponse(description = "Usuário retornado com sucesso", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro ao retornar usuário", responseCode = "400", content = @Content) })
    public ResponseEntity<Usuario> get(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (UsuarioNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MENSAGEM_ERRO_USUARIO_NAO_ENCONTRADO);
        }
    }

    /**
     * 
     * @param usuario Dados do usuário
     * @return Objeto usuário atualizado
     */
    @ResponseBody
    @PutMapping("/update")
    @Operation(summary = "Atualiza um usuário específico do banco de dados", responses = {
            @ApiResponse(description = "Usuário atualizado com sucesso", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro ao atualizar o usuário", responseCode = "400", content = @Content) })
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.ok(service.update(usuario));
        } catch (UsuarioNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Infelizmente o usuário não pode ser atualizado");
        }
    }

    /**
     * 
     * @param id identificação do usuário
     * @return objeto deletado
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta um usuário específico do banco de dados", responses = {
            @ApiResponse(description = "Usuário deletado com sucesso", responseCode = "200"),
            @ApiResponse(description = "Erro ao deletar o usuário", responseCode = "400") })
    public ResponseEntity<Usuario> delete(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (UsuarioNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não deletado");
        }
    }

}
