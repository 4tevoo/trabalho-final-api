package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCriar {

    @NotBlank(message = "O nome completo é obrigatório")
    @Schema(nullable = false, description = "Nome completo do cliente", example = "Thon braga")
    private String nomeCompleto;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "Formato de CPF inválido")
    @Schema(nullable = false, description = "CPF do cliente (apenas dígitos)", example = "12345678910")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "O telefone deve conter 11 dígitos")
    @Schema(nullable = false, description = "Telefone do cliente (apenas dígtos)", example = "21999999999",minLength = 11, maxLength = 11)
    private String telefone;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    @Schema(nullable = false, description = "E-mail do cliente", example = "thon@gmail.com")
    private String email;

    @NotBlank(message = "O CEP é obrigatório")
    @Pattern(regexp = "^[0-9]{8}$", message = "O CEP deve conter 8 dígitos")
    @Schema(nullable = false, description = "CEP do cliente", example = "28681335", minLength = 8, maxLength = 8)
    private String cep;

    @Schema(description = "Complemento do endereço", example = "Apto 05")
    private String complemento;

}

