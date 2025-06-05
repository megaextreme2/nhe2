package com.tcc.barbersclub.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.barbersclub.model.entity.Barbearia;

@Repository
public interface BarbeariaRepository extends JpaRepository<Barbearia, Long> {
    List<Barbearia> findByUsuarioId(Long usuarioId);
}
