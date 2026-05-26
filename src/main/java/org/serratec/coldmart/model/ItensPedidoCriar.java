package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoCriar {

    @NotNull(message = "O ID do curso é obrigatório")
    @Schema(nullable = false, description = "ID do curso que está sendo comprado", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID idCurso;
}
