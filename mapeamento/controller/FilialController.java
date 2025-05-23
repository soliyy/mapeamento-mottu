package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.FilialDTO;
import com.mottu.mapeamento.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/filiais")
public class FilialController {

    @Autowired
    private FilialService filialService;

    @PostMapping
    public ResponseEntity<FilialDTO> criar(@RequestBody FilialDTO dto) {
        return new ResponseEntity<>(filialService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FilialDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "idFilial") String ordenacao) {
        return ResponseEntity.ok(filialService.listarTodos(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filialService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialDTO> atualizar(@PathVariable Long id, @RequestBody FilialDTO dto) {
        return ResponseEntity.ok(filialService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
