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

import grupin.app.exception.ProjetoNaoEncontradoExeception;
import grupin.app.model.Projeto;
import grupin.app.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Lucas-dev-back
 */
@RestController
@RequestMapping(value = "/api/projetos")
@Tag(name = "Projeto")
@CrossOrigin(origins = "*")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    /**
     * 
     * @param projeto objeto json
     * @param arquivo arquivo .jpg ou .png
     * @return objeto salvo
     */
    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "Salva um projeto no banco de dados", responses = {
            @ApiResponse(description = "Projeto salvo com sucesso", responseCode = "201", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Erro ao salvar o projeto", responseCode = "400", content = @Content) })
    public ResponseEntity<Projeto> create(@RequestBody Projeto projeto, @RequestParam("file") MultipartFile arquivo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(projeto, arquivo));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Erro ao salvar o arquivo");
        }
    }

    /**
     * 
     * @param id identificação do projeto
     * @return projeto que está no banco de dados
     */
    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma projeto do banco de dados", responses = {
            @ApiResponse(description = "Projeto retornado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Erro ao retornar o projeto", responseCode = "400", content = @Content) })
    public ResponseEntity<Projeto> get(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (ProjetoNaoEncontradoExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * 
     * @return lista de projetos que estão no BD
     */
    @ResponseBody
    @GetMapping
    @Operation(summary = "Retorna todos os projetos do banco de dados", responses = {
            @ApiResponse(description = "Projetos retornados com sucesso", responseCode = "200", content = @Content) })
    public ResponseEntity<List<Projeto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * 
     * @param projeto objeto json que será atualizado
     * @return objeto atualizado
     */
    @ResponseBody
    @PutMapping("/update")
    @Operation(summary = "Atualiza um projeto no banco de dados", responses = {
            @ApiResponse(description = "Projeto atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Erro ao atualizar o projeto", responseCode = "400", content = @Content) })
    public ResponseEntity<Projeto> update(@RequestBody Projeto projeto) {
        try {
            return ResponseEntity.ok(service.update(projeto));
        } catch (ProjetoNaoEncontradoExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * 
     * @param id identificação do projeto
     * @return objeto removido
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta projeto do banco de dados", responses = {
            @ApiResponse(description = "Projeto deletado com sucesso", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro ao deletar projeto", responseCode = "400", content = @Content) })
    public ResponseEntity<Projeto> delete(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (ProjetoNaoEncontradoExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
