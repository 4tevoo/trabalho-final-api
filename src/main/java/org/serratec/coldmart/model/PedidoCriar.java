package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCriar {

    @NotNull(message = "O ID do cliente é obrigatório")
    @Schema(nullable = false, description = "O ID do cliente para criar o seu pedido", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID idCliente;

    @NotEmpty(message = "O pedido deve ter ao menos um curso")
    @Valid // verifica os dados da List
    @Schema(nullable = false, description = "Lista de cursos que o cliente adicionou no pedido")
    private List<ItensPedidoCriar> itens;
}
