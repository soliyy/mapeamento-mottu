package com.mottu.mapeamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "filial")
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFilial;

    @NotBlank(message = "O nome da filial é obrigatório")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(max = 255)
    private String endereco;

    @NotNull(message = "A capacidade do pátio é obrigatória")
    private Integer capacidadePatio;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patio> patios;

    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operador> operadores;
}
