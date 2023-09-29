package com.desafiobackend.model.dto;

import com.desafiobackend.model.Setor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DadosCadastraSetorDTO {

    private String nomeSetor;
    private List<DadosCadastroCargoDTO> cargos;


    public Setor transformarParaSetor(DadosCadastraSetorDTO dadosAtualizaSetorDTO) {
        return new Setor(dadosAtualizaSetorDTO);
    }
}
