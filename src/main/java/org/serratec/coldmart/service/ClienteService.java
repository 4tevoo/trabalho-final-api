package org.serratec.coldmart.service;

import lombok.RequiredArgsConstructor;
import org.serratec.coldmart.exceptions.NaoEncontradoException;
import org.serratec.coldmart.exceptions.EntidadeDuplicadaException;
import org.serratec.coldmart.exceptions.RegraNegocioException;
import org.serratec.coldmart.model.ViaCepResponse;
import org.serratec.coldmart.client.ViaCepClient;
import org.serratec.coldmart.model.ClienteBuscar;
import org.serratec.coldmart.model.ClienteCriar;
import org.serratec.coldmart.entity.Cliente;
import org.serratec.coldmart.repository.ClienteRepository;
import org.serratec.coldmart.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ViaCepClient viaCepClient;
    private final EmailService emailService;

    @Transactional
    public ClienteBuscar cadastrarCliente(ClienteCriar dto) {
        if (clienteRepository.findByCpf(dto.getCpf()).isPresent()) {
            throw new EntidadeDuplicadaException("O CPF " + dto.getCpf() + " já está cadastrado.");
        }

        boolean emailExiste = clienteRepository.findAll().stream()
                .anyMatch(c -> c.getEmail().equalsIgnoreCase(dto.getEmail()));

        if (emailExiste) {
            throw new EntidadeDuplicadaException("O E-mail " + dto.getEmail() + " já está cadastrado.");
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

        clienteRepository.findByCpf(dto.getCpf()).ifPresent(c -> {
            if (!c.getId().equals(id)) {
                throw new EntidadeDuplicadaException("O CPF " + dto.getCpf() + " já está cadastrado em outro usuário.");
            }
        });

        boolean emailExisteEmOutro = clienteRepository.findAll().stream()
                .anyMatch(c -> !c.getId().equals(id) && c.getEmail().equalsIgnoreCase(dto.getEmail()));

        if (emailExisteEmOutro) {
            throw new EntidadeDuplicadaException("O E-mail " + dto.getEmail() + " já está cadastrado em outro usuário.");
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

    @Transactional(readOnly = true)
    public ClienteBuscar buscarPorId(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente com ID " + id + " não encontrado."));
        return new ClienteBuscar(cliente);
    }

    @Transactional
    public void deletarCliente(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente com ID " + id + " não encontrado."));

        boolean possuiPedidos = !pedidoRepository.findByClienteId(id).isEmpty();

        if (possuiPedidos) {
            throw new RegraNegocioException("Não é possível excluir o cliente '" + cliente.getNomeCompleto() + "' porque ele possui histórico de pedidos no sistema.");
        }

        clienteRepository.delete(cliente);
    }
}