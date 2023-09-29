package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemCargoSemSetorDTO {

    private Long id;
    private String nomeCargo;

    public DadosListagemCargoSemSetorDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
    }
}
