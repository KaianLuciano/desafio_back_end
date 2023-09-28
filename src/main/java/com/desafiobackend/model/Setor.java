package com.desafiobackend.model;

import com.desafiobackend.model.dto.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.DadosCadastraSetorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "setor")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomeSetor;
    @OneToMany(mappedBy = "setor")
    private List<Cargo> cargos;
    @OneToMany(mappedBy = "setor")
    private List<Trabalhador> trabalhadores;

    public Setor(DadosAtualizaSetorDTO cadastroSetorDto) {
        this.nomeSetor = cadastroSetorDto.getNome();
    }

    public Setor(DadosCadastraSetorDTO dadosAtualizaSetorDTO) {
        this.nomeSetor = dadosAtualizaSetorDTO.getNomeSetor();
        this.cargos = dadosAtualizaSetorDTO.getCargos().stream().map(cargo -> new Cargo(cargo)).collect(Collectors.toList());
    }
}
