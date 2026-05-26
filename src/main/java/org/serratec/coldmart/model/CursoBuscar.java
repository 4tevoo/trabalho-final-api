package org.serratec.coldmart.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.serratec.coldmart.entity.Curso;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoBuscar {

    @Schema(description = "ID do curso", example = "264ce7a9-4308-4b05-a589-9fa0f96a111e")
    private UUID id;

    @Schema(description = "Título do curso", example = "Lógica de programação")
    private String titulo;

    @Schema(description = "Descrição detalhado do curso", example = "Fundamentos de programação do zero ao avançado")
    private String descricao;

    @Schema(description = "Preço do curso", example = "99.90")
    private Double preco;

    @Schema(description = "")
    private String nomeCategoria;

    public CursoBuscar(Curso curso) {
        this.id = curso.getId();
        this.titulo = curso.getTitulo();
        this.descricao = curso.getDescricao();
        this.preco = curso.getPreco();
        this.nomeCategoria = curso.getCategoria().getNome();
    }
}
