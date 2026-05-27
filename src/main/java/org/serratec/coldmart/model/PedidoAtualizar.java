package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.enums.FormaPagamento;
import org.serratec.coldmart.enums.StatusPagamento;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoAtualizar {

    @NotNull(message = "O status do pagamento é obrigatório")
    @Schema(nullable = false, description = "Atualizer o status de pagamento", example = "APROVADO")
    private StatusPagamento status;
    
    @Schema(description = "Selecione a forma de pagamento desejada", example = "CREDITO")
    private FormaPagamento formaPagamento;
}
