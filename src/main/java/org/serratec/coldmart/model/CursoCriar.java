package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoCriar {

    @NotBlank(message = "O título é obrigatório")
    @Schema(nullable = false, description = "Título do curso", example = "API Rest com JAVA e Sprig Boot")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Schema(nullable = false, description = "Descrição detalhada do curso", example = "Aprendendo API Rest do zero")
    private String descricao;

    @NotNull(message = "O preço é obrigatório")
    @PositiveOrZero(message = "Preço do curso deve ser maior ou igual a zero")
    @Schema(nullable = false, description = "Preço do curso", example = "99.90")
    private Double preco;

    @NotNull(message = "O ID da categoria do curso é obrigatório")
    @Schema(nullable = false, description = "ID da categoria do curso", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID idcategoria;


}
