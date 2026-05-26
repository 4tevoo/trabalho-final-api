package org.serratec.coldmart.controller;

import jakarta.validation.Valid;
import org.serratec.coldmart.model.CategoriaBuscar;
import org.serratec.coldmart.model.CategoriaCriar;
import org.serratec.coldmart.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarCategoria(@RequestBody @Valid CategoriaCriar dto) {
        categoriaService.cadastrarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaBuscar>> listarTodasCategorias() {
        List<CategoriaBuscar> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaBuscar> editarCategoria(@PathVariable UUID id, @RequestBody @Valid CategoriaCriar dto) {
        CategoriaBuscar categoriaEditada = categoriaService.editarCategoria(id, dto);
        return ResponseEntity.ok(categoriaEditada);

    }

}