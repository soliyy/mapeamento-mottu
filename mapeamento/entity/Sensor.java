package com.mottu.mapeamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSensor;

    @NotBlank(message = "O tipo do sensor é obrigatório")
    private String tipoSensor;

    @NotBlank(message = "O status do sensor é obrigatório")
    private String statusSensor;

    @OneToOne
    @JoinColumn(name = "moto_placa", nullable = false, unique = true)
    private Moto moto;
}
