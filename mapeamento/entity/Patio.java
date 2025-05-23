package com.mottu.mapeamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "patio")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatio;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    private String dimensao;

    @ManyToOne
    @JoinColumn(name = "filial_id_filial", nullable = false)
    private Filial filial;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Moto> motos;
}
