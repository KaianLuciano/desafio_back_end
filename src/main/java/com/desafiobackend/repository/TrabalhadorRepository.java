package com.desafiobackend.repository;

import com.desafiobackend.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
    boolean existsByCpf(String cpg);
}
