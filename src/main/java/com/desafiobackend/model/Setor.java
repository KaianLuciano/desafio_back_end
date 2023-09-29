package com.desafiobackend.model;

import com.desafiobackend.model.dto.setor.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.setor.DadosCadastraSetorDTO;
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

    public Setor(DadosAtualizaSetorDTO cadastroSetorDto, Setor setor) {
        this.id = setor.getId();
        this.nomeSetor = cadastroSetorDto.getNomeSetor() != null ? cadastroSetorDto.getNomeSetor() : setor.getNomeSetor();
        this.cargos = setor.getCargos();
    }

    public Setor(DadosCadastraSetorDTO dadosAtualizaSetorDTO) {
        this.nomeSetor = dadosAtualizaSetorDTO.getNomeSetor();
    }

    public Setor(long idSetor, String nomeSetor) {
        this.id = idSetor;
        this.nomeSetor = nomeSetor;
    }
}
