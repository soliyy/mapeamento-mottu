package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PatioDTO {

    private Long idPatio;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private String dimensao;

    private Long filialId;

    private List<MotoDTO> motos;
}
