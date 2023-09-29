package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.setor.DadosListagemTrabalhadorSetorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemSetorCargoDTO {

    private Long id;
    private String nomeCargo;

    public DadosListagemSetorCargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
    }
}
