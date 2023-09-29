package com.desafiobackend.repository;

import com.desafiobackend.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    boolean existsByNomeCargo(String nomeCargo);
}
