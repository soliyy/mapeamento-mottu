package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoDTO {

    private Long idMovimentacao;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String tipoMovimentacao;

    private String posicaoAnterior;
    private String posicaoNova;

    private String motoPlaca;
}
