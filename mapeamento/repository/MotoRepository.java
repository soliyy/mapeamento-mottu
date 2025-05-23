package com.mottu.mapeamento.repository;

import com.mottu.mapeamento.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, String> {
    List<Moto> findByStatusIgnoreCase(String status);
}
