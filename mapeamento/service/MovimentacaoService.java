package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.MovimentacaoDTO;
import com.mottu.mapeamento.entity.Movimentacao;
import com.mottu.mapeamento.entity.Moto;
import com.mottu.mapeamento.exception.ResourceNotFoundException;
import com.mottu.mapeamento.repository.MovimentacaoRepository;
import com.mottu.mapeamento.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MotoRepository motoRepository;

    @CacheEvict(value = "movimentacoes", allEntries = true)
    public MovimentacaoDTO criar(MovimentacaoDTO dto) {
        Movimentacao entidade = fromDTO(dto);
        Moto moto = motoRepository.findById(dto.getMotoPlaca())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        entidade.setMoto(moto);
        return toDTO(movimentacaoRepository.save(entidade));
    }

    @Cacheable("movimentacoes")
    public Page<MovimentacaoDTO> listarTodas(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Movimentacao> paginaMovimentacoes = movimentacaoRepository.findAll(pageable);
        return paginaMovimentacoes.map(this::toDTO);
    }

    public MovimentacaoDTO buscarPorId(Long id) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada com id: " + id));
        return toDTO(movimentacao);
    }

    @CacheEvict(value = "movimentacoes", allEntries = true)
    public MovimentacaoDTO atualizar(Long id, MovimentacaoDTO dto) {
        Movimentacao movimentacao = movimentacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada com id: " + id));

        movimentacao.setDataHora(dto.getDataHora());
        movimentacao.setTipoMovimentacao(dto.getTipoMovimentacao());
        movimentacao.setPosicaoAnterior(dto.getPosicaoAnterior());
        movimentacao.setPosicaoNova(dto.getPosicaoNova());

        Moto moto = motoRepository.findById(dto.getMotoPlaca())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        movimentacao.setMoto(moto);

        return toDTO(movimentacaoRepository.save(movimentacao));
    }

    @CacheEvict(value = "movimentacoes", allEntries = true)
    public void deletar(Long id) {
        Movimentacao movimentacao = buscarEntidadePorId(id);
        movimentacaoRepository.delete(movimentacao);
    }

    private Movimentacao buscarEntidadePorId(Long id) {
        return movimentacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada com id: " + id));
    }

    private Movimentacao fromDTO(MovimentacaoDTO dto) {
        Movimentacao m = new Movimentacao();
        m.setIdMovimentacao(dto.getIdMovimentacao());
        m.setDataHora(dto.getDataHora());
        m.setTipoMovimentacao(dto.getTipoMovimentacao());
        m.setPosicaoAnterior(dto.getPosicaoAnterior());
        m.setPosicaoNova(dto.getPosicaoNova());
        return m;
    }

    private MovimentacaoDTO toDTO(Movimentacao m) {
        MovimentacaoDTO dto = new MovimentacaoDTO();
        dto.setIdMovimentacao(m.getIdMovimentacao());
        dto.setDataHora(m.getDataHora());
        dto.setTipoMovimentacao(m.getTipoMovimentacao());
        dto.setPosicaoAnterior(m.getPosicaoAnterior());
        dto.setPosicaoNova(m.getPosicaoNova());
        dto.setMotoPlaca(m.getMoto().getPlaca());
        return dto;
    }
}
