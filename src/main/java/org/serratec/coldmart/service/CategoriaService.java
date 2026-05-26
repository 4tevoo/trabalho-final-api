package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.model.CategoriaBuscar;
import org.serratec.coldmart.model.CategoriaCriar;
import org.serratec.coldmart.entity.Categoria;
import org.serratec.coldmart.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    public CategoriaBuscar cadastrarCategoria(CategoriaCriar dto) {

        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new CategoriaBuscar(categoriaSalva);
    }

    @Transactional
    public CategoriaBuscar editarCategoria(UUID id, CategoriaCriar dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + id + " não encontrada."));

        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());

        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return new CategoriaBuscar(categoriaAtualizada);
    }

    @Transactional(readOnly = true)
    public List<CategoriaBuscar> listarTodas() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaBuscar::new)
                .collect(Collectors.toList());
    }
}