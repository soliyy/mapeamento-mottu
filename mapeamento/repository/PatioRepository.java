package com.mottu.mapeamento.repository;

import com.mottu.mapeamento.entity.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
    List<Patio> findByDescricaoContainingIgnoreCase(String descricao);
}
