package com.desafiobackend.model.dto.trabalhador;

import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.cargo.DadosListagemCargoComSetorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosListagemTrabalhadorDTO {

    private Long id;
    private String nome;
    private String cpf;
    private DadosListagemCargoComSetorDTO cargo;

    public DadosListagemTrabalhadorDTO(Trabalhador trabalhador) {
        this.id = trabalhador.getId();
        this.nome = trabalhador.getNome();
        this.cpf = trabalhador.getCpf();
        this.cargo = new DadosListagemCargoComSetorDTO(trabalhador.getCargo());
    }
}
