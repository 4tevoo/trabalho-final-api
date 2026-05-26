package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.entity.ItemPedido;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidosBuscar {

    @Schema(description = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID idItens;

    @Schema(description = "Nome do curso",example = "Lógica de Programação")
    private String tituloCurso;

    @Schema(description = "Valor do curso", example = "99.90")
    private Double valor;

    public ItensPedidosBuscar(ItemPedido itemPedido) {

        this.idItens = itemPedido.getId();
        this.tituloCurso = itemPedido.getCurso().getTitulo();
        this.valor = itemPedido.getValorVenda();
    }
}
