package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.exceptions.EntidadeDuplicadaException;
import org.serratec.coldmart.exceptions.RegraNegocioException;
import org.serratec.coldmart.model.CursoBuscar;
import org.serratec.coldmart.model.CursoCriar;
import org.serratec.coldmart.entity.Categoria;
import org.serratec.coldmart.entity.Curso;
import org.serratec.coldmart.repository.CategoriaRepository;
import org.serratec.coldmart.repository.CursoRepository;
import org.serratec.coldmart.repository.ItemPedidoRepository;
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
    private final ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public CursoBuscar cadastrarCurso(CursoCriar dto) {
        UUID categoriaId = dto.getIdcategoria();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + categoriaId + " não encontrada."));

        if (cursoRepository.existsByTituloAndCategoriaId(dto.getTitulo().trim(), categoriaId)) {
            throw new EntidadeDuplicadaException("O curso '" + dto.getTitulo() + "' já está cadastrado nesta categoria.");
        }

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

        if (dto.getIdcategoria() != null) {
            Categoria novaCategoria = categoriaRepository.findById(dto.getIdcategoria())
                    .orElseThrow(() -> new NaoEncontradoException("Categoria com ID " + dto.getIdcategoria() + " não encontrada."));
            curso.setCategoria(novaCategoria);
        }

        String tituloAntesDaMudanca = curso.getTitulo();
        curso.setTitulo(dto.getTitulo() != null ? dto.getTitulo() : curso.getTitulo());
        curso.setDescricao(dto.getDescricao() != null ? dto.getDescricao() : curso.getDescricao());
        curso.setPreco(dto.getPreco() != null ? dto.getPreco() : curso.getPreco());

        if (!curso.getTitulo().equalsIgnoreCase(tituloAntesDaMudanca) || dto.getIdcategoria() != null) {
            if (cursoRepository.existsByTituloAndCategoriaId(curso.getTitulo().trim(), curso.getCategoria().getId())) {
                throw new EntidadeDuplicadaException("Já existe outro curso com o título '" + curso.getTitulo() + "' nesta categoria.");
            }
        }

        Curso cursoAtualizado = cursoRepository.save(curso);
        return new CursoBuscar(cursoAtualizado);
    }

    @Transactional(readOnly = true)
    public List<CursoBuscar> listarTodos() {
        return cursoRepository.findAll().stream()
                .map(CursoBuscar::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CursoBuscar buscarPorId(UUID id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Curso com ID " + id + " não encontrado."));
        return new CursoBuscar(curso);
    }

    @Transactional
    public void deletarCurso(UUID id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Curso com ID " + id + " não encontrado."));

        boolean jaVendido = itemPedidoRepository.findAll().stream()
                .anyMatch(item -> item.getCurso() != null && item.getCurso().getId().equals(id));

        if (jaVendido) {
            throw new RegraNegocioException("Não é possível excluir o curso '" + curso.getTitulo() + "' porque ele está vinculado a pedidos existentes.");
        }

        cursoRepository.delete(curso);
    }
}