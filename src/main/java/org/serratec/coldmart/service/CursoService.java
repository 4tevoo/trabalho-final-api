package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.model.CursoBuscar;
import org.serratec.coldmart.model.CursoCriar;
import org.serratec.coldmart.entity.Categoria;
import org.serratec.coldmart.entity.Curso;
import org.serratec.coldmart.repository.CategoriaRepository;
import org.serratec.coldmart.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public CursoBuscar cadastrarCurso(CursoCriar dto) {
        UUID categoriaId = dto.getIdcategoria();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + categoriaId + " não encontrada."));

        Curso curso = new Curso();
        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setPreco(dto.getPreco());
        curso.setCategoria(categoria);

        Curso cursoSalvo = cursoRepository.save(curso);
        return new CursoBuscar(cursoSalvo);
    }

    @Transactional
    public CursoBuscar editarCurso(UUID id, CursoCriar dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Curso com ID " + id + " não encontrado."));

        // 🌟 UUID direto aqui também na edição
        UUID categoriaId = dto.getIdcategoria();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + categoriaId + " não encontrada."));

        curso.setTitulo(dto.getTitulo());
        curso.setDescricao(dto.getDescricao());
        curso.setPreco(dto.getPreco());
        curso.setCategoria(categoria);

        Curso cursoAtualizado = cursoRepository.save(curso);
        return new CursoBuscar(cursoAtualizado);
    }

    @Transactional(readOnly = true)
    public List<CursoBuscar> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(CursoBuscar::new)
                .collect(Collectors.toList());
    }
}