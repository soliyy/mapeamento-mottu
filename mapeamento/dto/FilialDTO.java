package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class FilialDTO {

    private Long idFilial;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 255)
    private String endereco;

    @NotNull(message = "A capacidade do pátio é obrigatória")
    private Integer capacidadePatio;

    private List<PatioDTO> patios;
}
