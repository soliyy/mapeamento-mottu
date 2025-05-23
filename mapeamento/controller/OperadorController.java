package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.OperadorDTO;
import com.mottu.mapeamento.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/operadores")
public class OperadorController {

    @Autowired
    private OperadorService operadorService;

    @PostMapping
    public ResponseEntity<OperadorDTO> criar(@RequestBody OperadorDTO dto) {
        return new ResponseEntity<>(operadorService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<OperadorDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "idOperador") String ordenacao) {
        return ResponseEntity.ok(operadorService.listarTodos(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperadorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(operadorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OperadorDTO> atualizar(@PathVariable Long id, @RequestBody OperadorDTO dto) {
        return ResponseEntity.ok(operadorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        operadorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-por-nome")
    public ResponseEntity<List<OperadorDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(operadorService.buscarPorNome(nome));
    }
}
