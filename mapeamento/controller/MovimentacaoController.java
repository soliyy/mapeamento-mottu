package com.mottu.mapeamento.controller;

import com.mottu.mapeamento.dto.MovimentacaoDTO;
import com.mottu.mapeamento.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> criar(@RequestBody MovimentacaoDTO dto) {
        return new ResponseEntity<>(movimentacaoService.criar(dto), CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MovimentacaoDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho,
            @RequestParam(defaultValue = "idMovimentacao") String ordenacao) {
        return ResponseEntity.ok(movimentacaoService.listarTodas(pagina, tamanho, ordenacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoDTO> atualizar(@PathVariable Long id, @RequestBody MovimentacaoDTO dto) {
        return ResponseEntity.ok(movimentacaoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        movimentacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
