package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaCriar {

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Schema(nullable = false,description = "Nome da categoria", example = "Frontend")
    private String nome;

    @NotBlank(message = "A descrição da categoria é obrigatória")
    @Schema(nullable = false,description = "Descrição da categoria", example = "Breve descrição descrição sobre a categoria")
    private String descricao;
}
