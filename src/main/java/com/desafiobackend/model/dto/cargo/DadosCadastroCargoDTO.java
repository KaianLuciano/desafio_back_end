package com.desafiobackend.model.dto.cargo;

import com.desafiobackend.model.Cargo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter @NoArgsConstructor
public class DadosCadastroCargoDTO {
    private String nomeCargo;

    public DadosCadastroCargoDTO(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }
}