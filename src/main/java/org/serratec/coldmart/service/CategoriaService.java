package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.EntidadeDuplicadaException;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.exceptions.RegraNegocioException;
import org.serratec.coldmart.model.CategoriaBuscar;
import org.serratec.coldmart.model.CategoriaCriar;
import org.serratec.coldmart.entity.Categoria;
import org.serratec.coldmart.repository.CategoriaRepository;
import org.serratec.coldmart.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CursoRepository cursoRepository;

    @Transactional
    public CategoriaBuscar cadastrarCategoria(CategoriaCriar dto) {
        if (!categoriaRepository.findByNomeIgnoreCase(dto.getNome().trim()).isEmpty()) {
            throw new EntidadeDuplicadaException("A categoria '" + dto.getNome() + "' já está cadastrada.");
        }

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

        List<Categoria> categoriasComMesmoNome = categoriaRepository.findByNomeIgnoreCase(dto.getNome().trim());
        for (Categoria c : categoriasComMesmoNome) {
            if (!c.getId().equals(id)) {
                throw new EntidadeDuplicadaException("Já existe outra categoria cadastrada com o nome '" + dto.getNome() + "'.");
            }
        }

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

    @Transactional(readOnly = true)
    public CategoriaBuscar buscarPorId(UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + id + " não encontrada."));
        return new CategoriaBuscar(categoria);
    }

    @Transactional
    public void deletarCategoria(UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + id + " não encontrada."));

        boolean possuiCursos = cursoRepository.findAll().stream()
                .anyMatch(curso -> curso.getCategoria() != null && curso.getCategoria().getId().equals(id));

        if (possuiCursos) {
            throw new RegraNegocioException("Não é possível excluir a categoria '" + categoria.getNome() + "' porque existem cursos vinculados a ela.");
        }

        categoriaRepository.delete(categoria);
    }
}