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
import org.serratec.coldmart.entity.Curso;
import org.serratec.coldmart.model.*;
import org.serratec.coldmart.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Curso")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Cadastrar curso")
    @ApiResponses(value = {
            @ApiResponse(description = "Curso cadastrado com sucesso", responseCode = "201"),
            @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<Void> cadastrarCurso(@RequestBody @Valid CursoCriar dto) {
        cursoService.cadastrarCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar todos os cursos")
    @ApiResponses(value = {
            @ApiResponse(description = "Todos os curso listados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping
    public ResponseEntity<List<CursoBuscar>> listarTodosCursos() {
        List<CursoBuscar> cursos = cursoService.listarTodos();
        return ResponseEntity.ok(cursos);
    }

    @Operation(summary = "Buscar curso pelo id do curso")
    @Parameters(value = {
            @Parameter(name = "id", description = "Buscar por id")})
    @ApiResponses(value = {
            @ApiResponse(description = "Curso retornado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<CursoBuscar> buscarCursoPorId(@PathVariable UUID id) {
        CursoBuscar curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @Operation(summary = "Atualizar curso")
    @Parameters(value = {
            @Parameter(name = "id", description = "Atualizar pelo id do curso")})
    @ApiResponses(value = {
            @ApiResponse(description = "Curso atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CursoCriar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<CursoBuscar> editarCursos(@PathVariable UUID id, @RequestBody @Valid CursoCriar dto) {
        CursoBuscar cursoEditado = cursoService.editarCurso(id, dto);
        return ResponseEntity.ok(cursoEditado);
    }

    @Operation(summary = "Remover curso")
    @Parameters(value = {
            @Parameter(name = "id", description = "Remover pelo id do curso")})
    @ApiResponses(value = {
            @ApiResponse(description = "Curso removido com sucesso", responseCode = "204"),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable UUID id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}