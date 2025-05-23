package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.PatioDTO;
import com.mottu.mapeamento.service.PatioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/patios")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @PostMapping
    public ResponseEntity<PatioDTO> criar(@RequestBody PatioDTO dto) {
        return new ResponseEntity<>(patioService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<PatioDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "idPatio") String ordenacao) {
        return ResponseEntity.ok(patioService.listarTodos(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(patioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatioDTO> atualizar(@PathVariable Long id, @RequestBody PatioDTO dto) {
        return ResponseEntity.ok(patioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        patioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-descricao")
    public ResponseEntity<List<PatioDTO>> buscarPorDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(patioService.buscarPorDescricao(descricao));
    }
}
