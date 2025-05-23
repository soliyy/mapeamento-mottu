package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.FilialDTO;
import com.mottu.mapeamento.entity.Filial;
import com.mottu.mapeamento.repository.FilialRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRepository;

    @CacheEvict(value = "filiais", allEntries = true)
    public FilialDTO criar(FilialDTO dto) {
        Filial entidade = fromDTO(dto);
        Filial salva = filialRepository.save(entidade);
        return toDTO(salva);
    }

    @Cacheable("filiais")
    public Page<FilialDTO> listarTodos(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Filial> paginaFiliais = filialRepository.findAll(pageable);
        return paginaFiliais.map(this::toDTO);
    }

    public FilialDTO buscarPorId(Long id) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        return toDTO(filial);
    }

    @CacheEvict(value = "filiais", allEntries = true)
    public FilialDTO atualizar(Long id, FilialDTO dto) {
        Filial filial = filialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        filial.setNome(dto.getNome());
        filial.setEndereco(dto.getEndereco());
        filial.setCapacidadePatio(dto.getCapacidadePatio());
        return toDTO(filialRepository.save(filial));
    }

    @CacheEvict(value = "filiais", allEntries = true)
    public void deletar(Long id) {
        if (!filialRepository.existsById(id)) {
            throw new EntityNotFoundException("Filial não encontrada");
        }
        filialRepository.deleteById(id);
    }

    private Filial fromDTO(FilialDTO dto) {
        Filial f = new Filial();
        f.setId(dto.getId());
        f.setNome(dto.getNome());
        f.setEndereco(dto.getEndereco());
        f.setCapacidadePatio(dto.getCapacidadePatio());
        return f;
    }

    private FilialDTO toDTO(Filial f) {
        FilialDTO dto = new FilialDTO();
        dto.setId(f.getId());
        dto.setNome(f.getNome());
        dto.setEndereco(f.getEndereco());
        dto.setCapacidadePatio(f.getCapacidadePatio());
        return dto;
    }
}
