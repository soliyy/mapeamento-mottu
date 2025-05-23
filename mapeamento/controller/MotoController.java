package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.MotoDTO;
import com.mottu.mapeamento.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @PostMapping
    public ResponseEntity<MotoDTO> criar(@RequestBody MotoDTO dto) {
        return new ResponseEntity<>(motoService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MotoDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "placa") String ordenacao) {
        return ResponseEntity.ok(motoService.listarTodos(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{placa}")
    public ResponseEntity<MotoDTO> buscarPorPlaca(@PathVariable String placa) {
        return ResponseEntity.ok(motoService.buscarPorPlaca(placa));
    }

    @PutMapping("/{placa}")
    public ResponseEntity<MotoDTO> atualizar(@PathVariable String placa, @RequestBody MotoDTO dto) {
        return ResponseEntity.ok(motoService.atualizar(placa, dto));
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deletar(@PathVariable String placa) {
        motoService.deletar(placa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-status")
    public ResponseEntity<List<MotoDTO>> buscarPorStatus(@RequestParam String status) {
        return ResponseEntity.ok(motoService.buscarPorStatus(status));
    }
}
