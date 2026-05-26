package org.serratec.coldmart.controller;

import jakarta.validation.Valid;
import org.serratec.coldmart.model.ClienteBuscar;
import org.serratec.coldmart.model.ClienteCriar;
import org.serratec.coldmart.service.ClienteService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarCliente(@RequestBody @Valid ClienteCriar dto) {
        clienteService.cadastrarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteBuscar>> listarTodosClientes() {
        List<ClienteBuscar> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteBuscar> editarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteCriar dto) {
        ClienteBuscar clienteEditado = clienteService.editarCliente(id, dto);
        return ResponseEntity.ok(clienteEditado);
    }
}
