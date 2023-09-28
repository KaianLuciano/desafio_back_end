package com.desafiobackend.model;

import com.desafiobackend.model.dto.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.DadosCadastroCargoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCargo;
    @ManyToOne
    @JoinColumn(name = "id_setor", unique = true)
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
