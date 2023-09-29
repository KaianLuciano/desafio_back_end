package com.desafiobackend.model;

import com.desafiobackend.model.dto.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.DadosCadastraSetorDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "setor")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeSetor;
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    private List<Cargo> cargos;
    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    private List<Trabalhador> trabalhadores;

    public Setor(DadosAtualizaSetorDTO cadastroSetorDto, Setor setor) {
        this.id = setor.getId();
        this.nomeSetor = cadastroSetorDto.getNomeSetor() != null ? cadastroSetorDto.getNomeSetor() : setor.getNomeSetor();
        this.cargos = setor.getCargos();
        this.trabalhadores = setor.getTrabalhadores();
    }

    public Setor(DadosAtualizaSetorDTO cadastroSetorDto) {
        this.nomeSetor = cadastroSetorDto.getNomeSetor();
    }

    public Setor(DadosCadastraSetorDTO dadosAtualizaSetorDTO) {
        this.nomeSetor = dadosAtualizaSetorDTO.getNomeSetor();
    }
}
