package com.mottu.mapeamento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimentacao;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String tipoMovimentacao;

    private String posicaoAnterior;
    private String posicaoNova;

    @ManyToOne
    @JoinColumn(name = "moto_placa", nullable = false)
    private Moto moto;


    public Long getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(Long idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(String tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public String getPosicaoAnterior() {
        return posicaoAnterior;
    }

    public void setPosicaoAnterior(String posicaoAnterior) {
        this.posicaoAnterior = posicaoAnterior;
    }

    public String getPosicaoNova() {
        return posicaoNova;
    }

    public void setPosicaoNova(String posicaoNova) {
        this.posicaoNova = posicaoNova;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}
