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
import org.serratec.coldmart.model.ErrorMessage;
import org.serratec.coldmart.model.PedidoAtualizar;
import org.serratec.coldmart.model.PedidoBuscar;
import org.serratec.coldmart.model.PedidoCriar;
import org.serratec.coldmart.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Pedido")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Cadastrar pedido")
    @ApiResponses(value = {
            @ApiResponse(description = "Pedido criado com sucesso", responseCode = "201"),
            @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<Void> criarPedido(@RequestBody @Valid PedidoCriar dto) {
        pedidoService.criarPedido(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Buscar pedido")
    @Parameters(value = {
            @Parameter(name = "id", description = "Buscar por id do pedido")})
    @ApiResponses(value = {
            @ApiResponse(description = "Lista de pedidos retornada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<PedidoBuscar> buscarPedidoPorId(@PathVariable UUID id) {
        PedidoBuscar pedido = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Listar todos os pedidos pelo cpf do cliente")
    @Parameters(value = {
            @Parameter(name = "cpf", description = "Listar pelo cpf do cliente")})
    @ApiResponses(value = {
            @ApiResponse(description = "Lista de pedidos pelo cpf, retornada com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),})
    @GetMapping("/cliente")
    public ResponseEntity<List<PedidoBuscar>> listarPedidosPorCpf(@RequestParam String cpf) {
        List<PedidoBuscar> pedidos = pedidoService.listarPedidosPorCpfCliente(cpf);
        return ResponseEntity.ok(pedidos);
    }

    @Operation(summary = "Atualizar status de pagamento do pedido")
    @Parameters(value = {
            @Parameter(name = "id", description = "Atualizar pelo id do pedido")})
    @ApiResponses(value = {
            @ApiResponse(description = "Status de pagamento atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoAtualizar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),})
    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoBuscar> atualizarStatusPagamentoPedido(@PathVariable UUID id, @RequestBody @Valid PedidoAtualizar dto) {
        PedidoBuscar pedidoEditado = pedidoService.atualizarStatusPagamento(id, dto);
        return ResponseEntity.ok(pedidoEditado);
    }

    @Operation(summary = "Remover pedido")
    @Parameters(value = {
            @Parameter(name = "id", description = "Remover pelo id do pedido")})
    @ApiResponses(value = {
            @ApiResponse(description = "Curso removido com sucesso", responseCode = "204"),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
