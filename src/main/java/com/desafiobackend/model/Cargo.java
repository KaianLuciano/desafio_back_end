package com.desafiobackend.model;

import com.desafiobackend.model.dto.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.DadosCadastroCargoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeCargo;

    @ManyToOne
    @JoinColumn(name = "id_setor")
    @JsonIgnore
    private Setor setor;

    @OneToMany(mappedBy = "cargo")
    private List<Trabalhador> trabalhadores;

    public Cargo(DadosCadastroCargoDTO cargo) {
        this.nomeCargo = cargo.getNomeCargo();
    }

    public Cargo(Cargo cargo, DadosAtualizaCargoDTO dadosCadastroCargoDTO) {
        this.nomeCargo = cargo.getNomeCargo();
    }
}
