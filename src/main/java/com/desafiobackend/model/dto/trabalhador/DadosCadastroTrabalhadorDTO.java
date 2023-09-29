package com.desafiobackend.model.dto.trabalhador;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class DadosCadastroTrabalhadorDTO {
    private String nome;
    private String cpf;
}
