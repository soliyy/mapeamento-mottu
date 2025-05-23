package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OperadorDTO {

    private Long idOperador;

    @NotBlank
    private String nome;

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    private Long filialId;
}
