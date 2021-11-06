package grupin.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import grupin.app.exception.MembroNaoEncontradoException;
import grupin.app.model.Membro;
import grupin.app.service.MembroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Lucas-dev-back
 */
@RestController
@RequestMapping(value = "/api/membros")
@Tag(name = "Membro")
@CrossOrigin(origins = "*")
public class MembroController {

    @Autowired
    private MembroService service;

    /**
     * 
     * @param id serve para identificar o id
     * @return o objeto do banco de dados
     */
    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Retorna um membro específico do banco de dados", responses = {
            @ApiResponse(description = "membro retornado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Membro.class))),
            @ApiResponse(description = "membro não encontrado", responseCode = "409", content = @Content) })
    public ResponseEntity<Membro> get(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (MembroNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Membro não encontrado");
        }
    }

    /**
     * 
     * @return Todos os membros do Banco de dados
     */
    @ResponseBody
    @GetMapping
    @Operation(summary = "Retorna todos os membros do banco de dados", responses = {
            @ApiResponse(description = "membros retornados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")) })
    public ResponseEntity<List<Membro>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * 
     * @param membro é um objeto json com as informações do membro
     * @return o objeto salvo
     */
    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "Salva um membro no banco de dados", responses = {
            @ApiResponse(description = "membro salvo com sucesso", responseCode = "201", content = @Content),
            @ApiResponse(description = "membro não salvo", responseCode = "400", content = @Content) })
    public ResponseEntity<Membro> create(@RequestBody Membro membro, @RequestParam("file") MultipartFile arquivo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(membro, arquivo));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Arquivo inválido ou não existe");
        }
    }

    /**
     * 
     * @param membro é um objeto json para dados do membro
     * @return o objeto atualizado
     */
    @ResponseBody
    @PutMapping("/update")
    @Operation(summary = "Atualiza um membro no banco de dados", responses = {
            @ApiResponse(description = "membro atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "membro NÃO atualizado", responseCode = "409") })
    public ResponseEntity<Membro> update(@RequestBody Membro membro) {
        try {
            return ResponseEntity.ok(service.update(membro));
        } catch (MembroNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Membro não atualizado");
        }
    }

    /**
     * 
     * @param id Identificação de quem será alterado o estado
     * @return Objeto inativo ou um conflito por não achar o membro
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Altera o status do membro para inativo", responses = {
            @ApiResponse(description = "inativação do membro ocorreu com sucesso", responseCode = "200"),
            @ApiResponse(description = "Erro ao inativar o membro", responseCode = "409") })
    public ResponseEntity<Membro> delete(@RequestParam int id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (MembroNaoEncontradoException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Erro ao inativar o membro");
        }
    }
}
