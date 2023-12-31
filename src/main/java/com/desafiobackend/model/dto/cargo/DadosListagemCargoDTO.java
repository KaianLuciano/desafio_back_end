package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.setor.DadosListagemSetorSemCargoDTO;
import com.desafiobackend.model.dto.trabalhador.DadosListagemTrabalhadorSemCargoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemCargoDTO {

    private Long id;
    private String nomeCargo;
    private DadosListagemSetorSemCargoDTO setor;
    private DadosListagemTrabalhadorSemCargoDTO trabalhador;

    public DadosListagemCargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
        this.setor = new DadosListagemSetorSemCargoDTO(cargo.getSetor());
        this.trabalhador = cargo.getTrabalhador() != null ? new DadosListagemTrabalhadorSemCargoDTO(cargo.getTrabalhador()) : null;
    }
}
