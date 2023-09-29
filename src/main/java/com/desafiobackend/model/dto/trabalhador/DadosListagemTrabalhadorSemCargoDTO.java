package com.desafiobackend.model.dto.trabalhador;

import com.desafiobackend.model.Trabalhador;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemTrabalhadorSemCargoDTO {

    private Long id;
    private String nome;
    private String cpf;

    public DadosListagemTrabalhadorSemCargoDTO(Trabalhador trabalhador) {
        this.id = trabalhador.getId();
        this.nome = trabalhador.getNome();
        this.cpf = trabalhador.getCpf();
    }
}
