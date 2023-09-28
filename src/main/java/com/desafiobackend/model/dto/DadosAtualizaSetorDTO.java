package com.desafiobackend.model.dto;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DadosAtualizaSetorDTO {

    private String nome;

    public Setor transformarParaSetor(DadosAtualizaSetorDTO dadosAtualizaSetorDTO) {
        return new Setor(dadosAtualizaSetorDTO);
    }
}
