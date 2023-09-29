package com.desafiobackend.model.dto.setor;

import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.cargo.DadosListagemCargoComTrabalhadorDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class DadosListagemSetorDTO {

    private Long id;
    private String nomeSetor;
    private List<DadosListagemCargoComTrabalhadorDTO> cargo;

    public DadosListagemSetorDTO(Setor setor) {
        this.id = setor.getId();
        this.nomeSetor = setor.getNomeSetor();
        this.cargo = setor.getCargos().stream().map(cargo -> new DadosListagemCargoComTrabalhadorDTO(cargo)).collect(Collectors.toList());
    }
}
