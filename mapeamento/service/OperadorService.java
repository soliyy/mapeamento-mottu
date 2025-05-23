package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.OperadorDTO;
import com.mottu.mapeamento.entity.Filial;
import com.mottu.mapeamento.entity.Operador;
import com.mottu.mapeamento.exception.ResourceNotFoundException;
import com.mottu.mapeamento.repository.FilialRepository;
import com.mottu.mapeamento.repository.OperadorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperadorService {

    @Autowired
    private OperadorRepository operadorRepository;

    @Autowired
    private FilialRepository filialRepository;

    @CacheEvict(value = "operadores", allEntries = true)
    public OperadorDTO criar(OperadorDTO dto) {
        Operador operador = fromDTO(dto);
        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        operador.setFilial(filial);
        return toDTO(operadorRepository.save(operador));
    }

    @Cacheable("operadores")
    public Page<OperadorDTO> listarTodos(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Operador> paginaOperadores = operadorRepository.findAll(pageable);
        return paginaOperadores.map(this::toDTO);
    }

    public OperadorDTO buscarPorId(Long id) {
        Operador operador = operadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operador não encontrado com id: " + id));
        return toDTO(operador);
    }

    @CacheEvict(value = "operadores", allEntries = true)
    public OperadorDTO atualizar(Long id, OperadorDTO dto) {
        Operador operador = operadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operador não encontrado com id: " + id));
        operador.setNome(dto.getNome());
        operador.setLogin(dto.getLogin());
        operador.setSenha(dto.getSenha());

        Filial filial = filialRepository.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
        operador.setFilial(filial);

        return toDTO(operadorRepository.save(operador));
    }

    @CacheEvict(value = "operadores", allEntries = true)
    public void deletar(Long id) {
        Operador operador = operadorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operador não encontrado com id: " + id));
        operadorRepository.delete(operador);
    }

    public List<OperadorDTO> buscarPorNome(String nome) {
        List<Operador> lista = operadorRepository.findByNomeContainingIgnoreCase(nome);
        return lista.stream().map(this::toDTO).toList();
    }

    private Operador fromDTO(OperadorDTO dto) {
        Operador o = new Operador();
        o.setIdOperador(dto.getIdOperador());
        o.setNome(dto.getNome());
        o.setLogin(dto.getLogin());
        o.setSenha(dto.getSenha());
        return o;
    }

    private OperadorDTO toDTO(Operador o) {
        OperadorDTO dto = new OperadorDTO();
        dto.setIdOperador(o.getIdOperador());
        dto.setNome(o.getNome());
        dto.setLogin(o.getLogin());
        dto.setSenha(o.getSenha());
        dto.setFilialId(o.getFilial().getIdFilial());
        return dto;
    }
}
