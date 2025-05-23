package com.mottu.mapeamento.service;

import com.mottu.mapeamento.dto.SensorDTO;
import com.mottu.mapeamento.entity.Moto;
import com.mottu.mapeamento.entity.Sensor;
import com.mottu.mapeamento.exception.ResourceNotFoundException;
import com.mottu.mapeamento.repository.MotoRepository;
import com.mottu.mapeamento.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MotoRepository motoRepository;

    @CacheEvict(value = "sensores", allEntries = true)
    public SensorDTO criar(SensorDTO dto) {
        Sensor sensor = fromDTO(dto);
        Moto moto = motoRepository.findById(dto.getMotoPlaca())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        sensor.setMoto(moto);
        return toDTO(sensorRepository.save(sensor));
    }

    @Cacheable("sensores")
    public Page<SensorDTO> listarTodos(int pagina, int tamanho, String ordenacao) {
        Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(ordenacao));
        Page<Sensor> paginaSensores = sensorRepository.findAll(pageable);
        return paginaSensores.map(this::toDTO);
    }

    public SensorDTO buscarPorId(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        return toDTO(sensor);
    }

    @CacheEvict(value = "sensores", allEntries = true)
    public SensorDTO atualizar(Long id, SensorDTO dto) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));

        sensor.setTipoSensor(dto.getTipoSensor());
        sensor.setStatusSensor(dto.getStatusSensor());

        Moto moto = motoRepository.findById(dto.getMotoPlaca())
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
        sensor.setMoto(moto);

        return toDTO(sensorRepository.save(sensor));
    }

    @CacheEvict(value = "sensores", allEntries = true)
    public void deletar(Long id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor não encontrado com id: " + id));
        sensorRepository.delete(sensor);
    }

    private Sensor fromDTO(SensorDTO dto) {
        Sensor s = new Sensor();
        s.setIdSensor(dto.getIdSensor());
        s.setTipoSensor(dto.getTipoSensor());
        s.setStatusSensor(dto.getStatusSensor());
        return s;
    }

    private SensorDTO toDTO(Sensor s) {
        SensorDTO dto = new SensorDTO();
        dto.setIdSensor(s.getIdSensor());
        dto.setTipoSensor(s.getTipoSensor());
        dto.setStatusSensor(s.getStatusSensor());
        dto.setMotoPlaca(s.getMoto().getPlaca());
        return dto;
    }
}
