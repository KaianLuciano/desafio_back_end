package com.desafiobackend.repository;

import com.desafiobackend.model.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorReporsitory extends JpaRepository<Setor, Long> {
}
