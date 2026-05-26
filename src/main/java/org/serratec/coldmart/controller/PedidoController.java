package org.serratec.coldmart.controller;

import jakarta.validation.Valid;
import org.serratec.coldmart.model.PedidoAtualizar;
import org.serratec.coldmart.model.PedidoBuscar;
import org.serratec.coldmart.model.PedidoCriar;
import org.serratec.coldmart.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody @Valid PedidoCriar dto) {
        pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoBuscar> buscarPedidoPorId(@PathVariable UUID id) {
        PedidoBuscar pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoBuscar> atualizarStatusPagamentoPedido(@PathVariable UUID id, @RequestBody @Valid PedidoAtualizar dto) {
        PedidoBuscar pedidoEditado = pedidoService.atualizarStatusPagamento(id, dto);
        return ResponseEntity.ok(pedidoEditado);
    }
}
