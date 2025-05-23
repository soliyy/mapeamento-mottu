package com.mottu.mapeamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "moto")
public class Moto {

    @Id
    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "O status é obrigatório")
    private String status;

    private String localizacaoAtual;

    @ManyToOne
    @JoinColumn(name = "patio_id_patio", nullable = false)
    private Patio patio;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Sensor sensor;

    @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes;
}
