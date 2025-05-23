package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.MotoDTO;
import com.mottu.mapeamento.entity.Moto;
import com.mottu.mapeamento.entity.Patio;
import com.mottu.mapeamento.repository.MotoRepository;
import com.mottu.mapeamento.repository.PatioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private PatioRepository patioRepository;

    @CacheEvict(value = "motos", allEntries = true)
    public MotoDTO criar(MotoDTO dto) {
        Moto moto = fromDTO(dto);
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        moto.setPatio(patio);
        return toDTO(motoRepository.save(moto));
    }

    @Cacheable("motos")
    public Page<MotoDTO> listarTodos(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Moto> paginaMotos = motoRepository.findAll(pageable);
        return paginaMotos.map(this::toDTO);
    }

    public MotoDTO buscarPorPlaca(String placa) {
        Moto moto = motoRepository.findById(placa).orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        return toDTO(moto);
    }

    @CacheEvict(value = "motos", allEntries = true)
    public MotoDTO atualizar(String placa, MotoDTO dto) {
        Moto moto = motoRepository.findById(placa).orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        moto.setModelo(dto.getModelo());
        moto.setAno(dto.getAno());
        moto.setStatus(dto.getStatus());
        moto.setLocalizacaoAtual(dto.getLocalizacaoAtual());
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));
        moto.setPatio(patio);
        return toDTO(motoRepository.save(moto));
    }

    @CacheEvict(value = "motos", allEntries = true)
    public void deletar(String placa) {
        if (!motoRepository.existsById(placa)) throw new EntityNotFoundException("Moto não encontrada");
        motoRepository.deleteById(placa);
    }

    public List<MotoDTO> buscarPorStatus(String status) {
        List<Moto> lista = motoRepository.findByStatusIgnoreCase(status);
        return lista.stream().map(this::toDTO).toList();
    }

    private Moto fromDTO(MotoDTO dto) {
        Moto m = new Moto();
        m.setPlaca(dto.getPlaca());
        m.setModelo(dto.getModelo());
        m.setAno(dto.getAno());
        m.setStatus(dto.getStatus());
        m.setLocalizacaoAtual(dto.getLocalizacaoAtual());
        return m;
    }

    private MotoDTO toDTO(Moto m) {
        MotoDTO dto = new MotoDTO();
        dto.setPlaca(m.getPlaca());
        dto.setModelo(m.getModelo());
        dto.setAno(m.getAno());
        dto.setStatus(m.getStatus());
        dto.setLocalizacaoAtual(m.getLocalizacaoAtual());
        dto.setPatioId(m.getPatio().getIdPatio());
        return dto;
    }
}
