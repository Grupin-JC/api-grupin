package grupin.app.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import grupin.app.exception.CategoriaNaoEncontradaExeception;
import grupin.app.model.Categoria;
import grupin.app.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Lucas-dev-back
 */
@RestController
@RequestMapping(value = "/api/categorias")
@Tag(name = "Categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    /**
     * 
     * @param categoria objeto json
     * @return objeto salvo
     */
    @ResponseBody
    @PostMapping("/create")
    @Operation(summary = "Salva uma categoria no banco de dados", responses = {
            @ApiResponse(description = "Categoria salva com sucesso", responseCode = "200", content = @Content) })
    public ResponseEntity<Categoria> create(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(categoria));
    }

    /**
     * 
     * @param id identificação da categoria
     * @return Categoria específica do banco de dados
     */
    @ResponseBody
    @GetMapping("/{id}")
    @Operation(summary = "Retorna uma categoria específica do banco de dados", responses = {
            @ApiResponse(description = "Categoria retornada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Erro ao retornar categoria", responseCode = "400", content = @Content) })
    public ResponseEntity<Categoria> get(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch (CategoriaNaoEncontradaExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
        }
    }

    /**
     * 
     * @return lista de categorias que estão no BD
     */
    @ResponseBody
    @GetMapping
    @Operation(summary = "Retorna todos as categorias do banco de dados", responses = {
            @ApiResponse(description = "lista de categorias retornada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")) })
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * 
     * @param categoria Objeto que será atualizado
     * @return Objeto atualizado
     */
    @ResponseBody
    @PutMapping("/update")
    @Operation(summary = "Atualiza uma categoria específica do banco de dados", responses = {
            @ApiResponse(description = "Categoria atualizada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json")),
            @ApiResponse(description = "Erro ao atualizar a categoria", responseCode = "400", content = @Content) })
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(service.update(categoria));
        } catch (CategoriaNaoEncontradaExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
        }
    }

    /**
     * 
     * @param id Identificação da categoria
     * @return Categoria deletada
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleta uma categoria específica do banco de dados", responses = {
            @ApiResponse(description = "Categoria deletado com sucesso", responseCode = "200", content = @Content),
            @ApiResponse(description = "Erro ao deletar categoria", responseCode = "400", content = @Content) })
    public ResponseEntity<Categoria> delete(@PathVariable(value = "id") int id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (CategoriaNaoEncontradaExeception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada");
        }
    }

}
