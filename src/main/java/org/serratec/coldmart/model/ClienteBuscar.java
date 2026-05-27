package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.entity.Cliente;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteBuscar {

    @Schema(description = "ID clinete", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID id;

    @Schema(description = "Nome Completo do clinete", example = "Thon Braga")
    private String nomeCompleto;

    @Schema(description = "CPF do cliente", example = "99155253970")
    private String cpf;

    @Schema(description = "E-mail do cliente", example = "thon@gmail.com")
    private String email;

    @Schema(description = "Telefone do cliente", example = "21999999999")
    private String telefone;

    @Schema(description = "CEP do clinete", example = "28681335")
    private String cep;

    @Schema(description = "Nome da rua do cliente", example = "Rua Oswaldo aranha")
    private String logradouro;

@Schema(description = "Complemento do endereço", example = "Apto 10")
    private String complemento;

    @Schema(description = "Biarro do cliente", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade do cliente", example = "Cachoeiras de Macacu")
    private String localidade;

    @Schema(description = "Estado do clinete", example = "RJ")
    private String uf;

    public ClienteBuscar(Cliente cliente) {
        this.id = cliente.getId();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = formatCPF(cliente.getCpf());
        this.email = cliente.getEmail();
        this.telefone = formatTelefone(cliente.getTelefone());
        this.cep = cliente.getCep();
        this.logradouro = cliente.getLogradouro();
        this.complemento = cliente.getComplemento();
        this.bairro = cliente.getBairro();
        this.localidade = cliente.getLocalidade();
        this.uf = cliente.getUf();
    }

    private String formatCPF(String cpf) {
        if ( cpf == null || cpf.length() != 11) {return cpf;}
        return  cpf.substring(0, 3) + "." +
                cpf.substring(3,6) + "." +
                cpf.substring(6,9) + "-" +
                cpf.substring(9);
    }

    private String formatTelefone(String telefone) {
        if (telefone == null || telefone.length() != 11) {return telefone;}
        return "(" + telefone.substring(0,2) + ")" +
                telefone.substring(2,6) + "-" +
                telefone.substring(6);
    }
}
