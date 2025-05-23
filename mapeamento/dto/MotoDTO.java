package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MotoDTO {

    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    private String localizacaoAtual;

    private Long patioId;

    private SensorDTO sensor;

    private List<MovimentacaoDTO> movimentacoes;
}
