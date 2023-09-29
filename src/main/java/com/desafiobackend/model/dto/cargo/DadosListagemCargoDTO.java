package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.setor.DadosListagemTrabalhadorSetorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosListagemCargoTrabalhadorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemCargoDTO {

    private Long id;
    private String nomeCargo;
    private DadosListagemTrabalhadorSetorDTO setor;
    private DadosListagemCargoTrabalhadorDTO trabalhador;

    public DadosListagemCargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
        this.setor = new DadosListagemTrabalhadorSetorDTO(cargo.getSetor());
        this.trabalhador = cargo.getTrabalhador() != null ? new DadosListagemCargoTrabalhadorDTO(cargo.getTrabalhador()) : null;
    }
}
