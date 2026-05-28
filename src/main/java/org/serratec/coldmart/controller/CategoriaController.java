package org.serratec.coldmart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.serratec.coldmart.model.CategoriaBuscar;
import org.serratec.coldmart.model.CategoriaCriar;
import org.serratec.coldmart.model.ErrorMessage;
import org.serratec.coldmart.model.PedidoAtualizar;
import org.serratec.coldmart.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Categorias")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Cadastrar categoria")
    @ApiResponses(value = {
            @ApiResponse(description = "Categoria cadastrada com sucesso", responseCode = "201"),
            @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<Void> cadastrarCategoria(@RequestBody @Valid CategoriaCriar dto) {
        categoriaService.cadastrarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar todos as categorias")
    @ApiResponses(value = {
            @ApiResponse(description = "Todas as categorias listadas com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping
    public ResponseEntity<List<CategoriaBuscar>> listarTodasCategorias() {
        List<CategoriaBuscar> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);

    }

    @Operation(summary = "Buscar categoria por ID")
    @Parameters(value = {
            @Parameter(name = "id", description = "Buscar pelo id da categoria")})
    @ApiResponses(value = {
            @ApiResponse(description = "Categoria retornada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaBuscar> buscarCategoriaPorId(@PathVariable UUID id) {
        CategoriaBuscar categoria = categoriaService.buscarPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @Operation(summary = "Atualizar categoria")
    @Parameters(value = {
            @Parameter(name = "id", description = "Atualizar pelo id da categoria")})
    @ApiResponses(value = {
            @ApiResponse(description = "Categoria atualizada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriaCriar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaBuscar> editarCategoria(@PathVariable UUID id, @RequestBody CategoriaCriar dto) {
        CategoriaBuscar categoriaEditada = categoriaService.editarCategoria(id, dto);
        return ResponseEntity.ok(categoriaEditada);
    }

    @Operation(summary = "Remover categoria")
    @Parameters(value = {
            @Parameter(name = "id", description = "Remover pelo id da categoria")})
    @ApiResponses(value = {
            @ApiResponse(description = "Categoria removida com sucesso", responseCode = "204"),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
