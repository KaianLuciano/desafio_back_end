package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.trabalhador.DadosListagemTrabalhadorSemCargoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemCargoComTrabalhadorDTO {

    private Long id;
    private String nomeCargo;
    private DadosListagemTrabalhadorSemCargoDTO trabalhador;

    public DadosListagemCargoComTrabalhadorDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
        this.trabalhador = cargo.getTrabalhador() != null ? new DadosListagemTrabalhadorSemCargoDTO(cargo.getTrabalhador()) : null;
    }
}
