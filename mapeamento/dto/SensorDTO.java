package com.mottu.mapeamento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SensorDTO {

    private Long idSensor;

    @NotBlank(message = "O tipo do sensor é obrigatório")
    private String tipoSensor;

    @NotBlank(message = "O status do sensor é obrigatório")
    private String statusSensor;

    private String motoPlaca;
}
