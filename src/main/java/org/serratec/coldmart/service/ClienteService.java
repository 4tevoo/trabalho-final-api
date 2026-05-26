package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.exceptions.EntidadeDuplicadaException;
import org.serratec.coldmart.model.ViaCepResponse;
import org.serratec.coldmart.client.ViaCepClient;
import org.serratec.coldmart.model.ClienteBuscar;
import org.serratec.coldmart.model.ClienteCriar;
import org.serratec.coldmart.entity.Cliente;
import org.serratec.coldmart.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ViaCepClient viaCepClient;
    private final EmailService emailService;

    @Transactional
    public ClienteBuscar cadastrarCliente(ClienteCriar dto) {
        List<Cliente> todosClientes = clienteRepository.findAll();

        for (Cliente c : todosClientes) {
            if (c.getCpf().equals(dto.getCpf())) {
                throw new EntidadeDuplicadaException("O CPF " + dto.getCpf() + " já está cadastrado.");
            }
            if (c.getEmail().equalsIgnoreCase(dto.getEmail())) {
                throw new EntidadeDuplicadaException("O E-mail " + dto.getEmail() + " já está cadastrado.");
            }
        }

        Cliente cliente = new Cliente();
        preencherDadosCliente(cliente, dto);

        Cliente clienteSalvo = clienteRepository.save(cliente);
        emailService.enviarEmailCadastro(clienteSalvo.getEmail(), clienteSalvo.getNomeCompleto(), "Seu cadastro na ColdMart foi realizado com sucesso!");

        return new ClienteBuscar(clienteSalvo);
    }

    @Transactional
    public ClienteBuscar editarCliente(UUID id, ClienteCriar dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente com ID " + id + " não encontrado."));

        List<Cliente> todosClientes = clienteRepository.findAll();
        for (Cliente c : todosClientes) {
            if (!c.getId().equals(id)) {
                if (c.getCpf().equals(dto.getCpf())) {
                    throw new EntidadeDuplicadaException("O CPF " + dto.getCpf() + " já está cadastrado em outro usuário.");
                }
                if (c.getEmail().equalsIgnoreCase(dto.getEmail())) {
                    throw new EntidadeDuplicadaException("O E-mail " + dto.getEmail() + " já está cadastrado em outro usuário.");
                }
            }
        }

        preencherDadosCliente(cliente, dto);
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        emailService.enviarEmailCadastro(clienteAtualizado.getEmail(), clienteAtualizado.getNomeCompleto(), "Seus dados no ColdMart foram alterados com sucesso!");

        return new ClienteBuscar(clienteAtualizado);
    }

    @Transactional(readOnly = true)
    public List<ClienteBuscar> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteBuscar::new)
                .collect(Collectors.toList());
    }

    private void preencherDadosCliente(Cliente cliente, ClienteCriar dto) {
        cliente.setNomeCompleto(dto.getNomeCompleto());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setCep(dto.getCep());
        cliente.setComplemento(dto.getComplemento());

        ViaCepResponse viaCep = viaCepClient.buscarCep(dto.getCep());
        if (viaCep != null) {
            cliente.setLogradouro(viaCep.logradouro());
            cliente.setBairro(viaCep.bairro());
            cliente.setLocalidade(viaCep.localidade());
            cliente.setUf(viaCep.uf());
        }
    }
}