package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.enums.StatusPagamento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoBuscar {

    @Schema(description = "ID do pedido", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID idPedido;

    @Schema(description = "Data do pedido", example = "2026-05-25")
    private LocalDate dataPedido;

    @Schema(description = "Status do pagamento do pedido", example = "PROCESSANDO")
    private StatusPagamento status;

    @Schema(description = "Nome do cliente", example = "Thon Braga")
    private String nomeCliente;

    @Schema(description = "Lista dos cursos adicionandos", example = "Lógica de Programação")
    private List<ItensPedidosBuscar> itens;

    @Schema(description = "Valor total", example = "99.90")
    private Double valorTotal;
}
