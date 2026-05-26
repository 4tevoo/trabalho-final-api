package org.serratec.coldmart.controller;

import jakarta.validation.Valid;
import org.serratec.coldmart.entity.Curso;
import org.serratec.coldmart.model.ClienteBuscar;
import org.serratec.coldmart.model.CursoBuscar;
import org.serratec.coldmart.model.CursoCriar;
import org.serratec.coldmart.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarCurso(@RequestBody @Valid CursoCriar dto) {
        cursoService.cadastrarCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CursoBuscar>> listarTodosCursos() {
        List<CursoBuscar> cursos = cursoService.listarTodos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoBuscar> buscarCursoPorId(@PathVariable UUID id) {
        CursoBuscar curso = cursoService.buscarPorId(id);
        return ResponseEntity.ok(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoBuscar> editarCursos(@PathVariable UUID id, @RequestBody @Valid CursoCriar dto) {
        CursoBuscar cursoEditado = cursoService.editarCurso(id, dto);
        return ResponseEntity.ok(cursoEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable UUID id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}