package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.dto.setor.DadosListagemSetorSemCargoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemCargoComSetorDTO {

    private Long id;
    private String nomeCargo;
    private DadosListagemSetorSemCargoDTO setor;

    public DadosListagemCargoComSetorDTO(Cargo cargo) {
        this.id = cargo.getId();
        this.nomeCargo = cargo.getNomeCargo();
        this.setor = new DadosListagemSetorSemCargoDTO(cargo.getSetor());
    }
}
