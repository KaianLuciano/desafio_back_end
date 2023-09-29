package com.desafiobackend.model.dto.setor;

import com.desafiobackend.model.Setor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemTrabalhadorSetorDTO {

    private Long id;
    private String nomeSetor;

    public DadosListagemTrabalhadorSetorDTO(Setor setor) {
        this.id = setor.getId();
        this.nomeSetor = setor.getNomeSetor();
    }
}
