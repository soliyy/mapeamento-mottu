package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.PatioDTO;
import com.mottu.mapeamento.entity.Filial;
import com.mottu.mapeamento.entity.Patio;
import com.mottu.mapeamento.exception.ResourceNotFoundException;
import com.mottu.mapeamento.repository.FilialRepository;
import com.mottu.mapeamento.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    @Autowired
    private FilialRepository filialRepository;

    @CacheEvict(value = "patios", allEntries = true)
    public PatioDTO criar(PatioDTO dto) {
        Patio patio = fromDTO(dto);
        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        patio.setFilial(filial);
        return toDTO(patioRepository.save(patio));
    }

    @Cacheable("patios")
    public Page<PatioDTO> listarTodos(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Patio> paginaPatios = patioRepository.findAll(pageable);
        return paginaPatios.map(this::toDTO);
    }

    public PatioDTO buscarPorId(Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pátio não encontrado com id: " + id));
        return toDTO(patio);
    }

    @CacheEvict(value = "patios", allEntries = true)
    public PatioDTO atualizar(Long id, PatioDTO dto) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pátio não encontrado com id: " + id));
        patio.setDescricao(dto.getDescricao());
        patio.setDimensao(dto.getDimensao());

        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        patio.setFilial(filial);

        return toDTO(patioRepository.save(patio));
    }

    @CacheEvict(value = "patios", allEntries = true)
    public void deletar(Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pátio não encontrado com id: " + id));
        patioRepository.delete(patio);
    }

    public List<PatioDTO> buscarPorDescricao(String descricao) {
        List<Patio> lista = patioRepository.findByDescricaoContainingIgnoreCase(descricao);
        return lista.stream().map(this::toDTO).toList();
    }

    private Patio fromDTO(PatioDTO dto) {
        Patio p = new Patio();
        p.setIdPatio(dto.getIdPatio());
        p.setDescricao(dto.getDescricao());
        p.setDimensao(dto.getDimensao());
        return p;
    }

    private PatioDTO toDTO(Patio p) {
        PatioDTO dto = new PatioDTO();
        dto.setIdPatio(p.getIdPatio());
        dto.setDescricao(p.getDescricao());
        dto.setDimensao(p.getDimensao());
        dto.setFilialId(p.getFilial().getIdFilial());
        return dto;
    }
}
