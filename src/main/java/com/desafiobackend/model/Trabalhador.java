package com.desafiobackend.model;

import com.desafiobackend.model.dto.trabalhador.DadosAtualizaTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosCadastroTrabalhadorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "trabalhador")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Trabalhador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JsonIgnore
    private Cargo cargo;
    private String nome;
    @Column(unique = true, length = 11)
    private String cpf;

    public Trabalhador(DadosCadastroTrabalhadorDTO dadosCadastroTrabalhador) {
        this.nome = dadosCadastroTrabalhador.getNome();
        this.cpf = dadosCadastroTrabalhador.getCpf();
    }

    public Trabalhador(Trabalhador trabalhador, DadosAtualizaTrabalhadorDTO dadosAtualizaTrabalhador) {
        this.id = trabalhador.getId();
        this.cargo = trabalhador.getCargo();
        this.nome = dadosAtualizaTrabalhador.getNome() != null ? dadosAtualizaTrabalhador.getNome() : trabalhador.nome;
        this.cpf = dadosAtualizaTrabalhador.getCpf() != null ? dadosAtualizaTrabalhador.getCpf() : trabalhador.getCpf();
    }
}
