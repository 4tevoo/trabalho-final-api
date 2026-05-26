package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.entity.Categoria;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaBuscar {

    @Schema(description = "ID da categoria", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID id;

    @Schema(description = "Nome da categoria", example = "Lógica de programação")
    private String nome;

    @Schema(description = "Descrição da categoria", example = "Breve descrição descrição sobre a categoria")
    private String descricao;

    public CategoriaBuscar(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
    }
}
