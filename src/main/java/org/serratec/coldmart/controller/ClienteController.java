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
import org.serratec.coldmart.model.*;
import org.serratec.coldmart.service.ClienteService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Cliente")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Cadastrar cliente")
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente cadastrado com sucesso", responseCode = "201"),
            @ApiResponse(description = "Dados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid ClienteCriar dto) {
        clienteService.cadastrarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(description = "Todos os clientes listados com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping
    public ResponseEntity<List<ClienteBuscar>> listarTodosClientes() {
        List<ClienteBuscar> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Buscar cliente por ID")
    @Parameters(value = {
            @Parameter(name = "id", description = "Buscar pelo id do cliente")})
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente retornado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteBuscar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<ClienteBuscar> buscarClientePorId(@PathVariable UUID id) {
        ClienteBuscar cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Atualizar cliente")
    @Parameters(value = {
            @Parameter(name = "id", description = "Atualizar pelo id do cliente")})
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente atualizado com sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteCriar.class))),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados já cadastrados", responseCode = "409", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<ClienteBuscar> editarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteCriar dto) {
        ClienteBuscar clienteEditado = clienteService.editarCliente(id, dto);
        return ResponseEntity.ok(clienteEditado);
    }

    @Operation(summary = "Remover cliente")
    @Parameters(value = {
            @Parameter(name = "id", description = "Remover pelo id do cliente")})
    @ApiResponses(value = {
            @ApiResponse(description = "Cliente removido com sucesso", responseCode = "204"),
            @ApiResponse(description = "Dados informados inválidos", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Dados não encontrados", responseCode = "404", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(description = "Erro interno no servidor", responseCode = "500", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
