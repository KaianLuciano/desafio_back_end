package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.setor.DadosListagemTrabalhadorSetorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemTrabalhadorCargoDTO {

    private Long id;
    private String nomeCargo;
    private DadosListagemTrabalhadorSetorDTO setor;

    public DadosListagemTrabalhadorCargoDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
        this.setor = new DadosListagemTrabalhadorSetorDTO(cargo.getSetor());
    }
}
