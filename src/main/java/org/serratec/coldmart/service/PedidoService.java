package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.model.*;
import org.serratec.coldmart.entity.*;
import org.serratec.coldmart.enums.StatusPagamento;
import org.serratec.coldmart.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itensPedidosRepository;
    private final ClienteRepository clienteRepository;
    private final CursoRepository cursoRepository;

    @Transactional
    public PedidoBuscar criarPedido(PedidoCriar dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new NaoEncontradoException("Cliente com ID " + dto.getIdCliente() + " não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(StatusPagamento.PROCESSANDO);

        pedido.setItens(new ArrayList<>());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        double valorTotalPedido = 0.0;

        for (ItensPedidoCriar itemDto : dto.getItens()) {
            Curso curso = cursoRepository.findById(itemDto.getIdCurso())
                    .orElseThrow(() -> new NaoEncontradoException("Curso com ID " + itemDto.getIdCurso() + " não encontrado."));

            ItemPedido item = new ItemPedido();
            item.setPedido(pedidoSalvo);
            item.setCurso(curso);
            item.setValorVenda(curso.getPreco());

            item.setQuantidade(1);
            item.setDesconto(0.0);

            valorTotalPedido += curso.getPreco();

            itensPedidosRepository.save(item);
            pedidoSalvo.getItens().add(item);
        }

        return mapearParaPedidoBuscar(pedidoSalvo, valorTotalPedido);
    }

    @Transactional
    public PedidoBuscar atualizarStatusPagamento(UUID id, PedidoAtualizar dto) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Pedido com ID " + id + " não encontrado."));

        pedido.setStatus(dto.getStatus());
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        double valorTotal = pedidoAtualizado.getItens().stream()
                .mapToDouble(ItemPedido::getValorVenda)
                .sum();

        return mapearParaPedidoBuscar(pedidoAtualizado, valorTotal);
    }

    @Transactional(readOnly = true)
    public PedidoBuscar buscarPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Pedido de número " + id + " não encontrado."));

        double valorTotal = pedido.getItens().stream()
                .mapToDouble(ItemPedido::getValorVenda)
                .sum();

        return mapearParaPedidoBuscar(pedido, valorTotal);
    }

    private PedidoBuscar mapearParaPedidoBuscar(Pedido pedido, double valorTotal) {
        PedidoBuscar response = new PedidoBuscar();
        response.setIdPedido(pedido.getId());
        response.setDataPedido(pedido.getDataPedido());
        response.setStatus(pedido.getStatus());
        response.setNomeCliente(pedido.getCliente().getNomeCompleto());

        List<ItensPedidosBuscar> itensBuscar = pedido.getItens().stream()
                .map(ItensPedidosBuscar::new)
                .collect(Collectors.toList());

        response.setItens(itensBuscar);
        response.setValorTotal(valorTotal);

        return response;
    }
}