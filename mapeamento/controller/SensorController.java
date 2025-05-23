package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.SensorDTO;
import com.mottu.mapeamento.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping
    public ResponseEntity<SensorDTO> criar(@RequestBody SensorDTO dto) {
        return new ResponseEntity<>(sensorService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<SensorDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "idSensor") String ordenacao) {
        return ResponseEntity.ok(sensorService.listarTodos(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sensorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorDTO> atualizar(@PathVariable Long id, @RequestBody SensorDTO dto) {
        return ResponseEntity.ok(sensorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sensorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
