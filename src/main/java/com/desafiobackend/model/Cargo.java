package com.desafiobackend.model;

import com.desafiobackend.exception.DadoNaoEncontradoException;
import com.desafiobackend.model.dto.cargo.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private Setor setor;

    @OneToOne(mappedBy = "cargo", cascade = CascadeType.ALL)
    private Trabalhador trabalhador;

    public Cargo(DadosCadastroCargoDTO cargo) {
        if(cargo.getNomeCargo() == ""){throw new DadoNaoEncontradoException("Nome cargo não preenchido");}
        this.nomeCargo = cargo.getNomeCargo();
    }

    public Cargo(Cargo cargo, DadosAtualizaCargoDTO dadosAtualizaCargoDTO) {
        this.id = cargo.getId();
        this.nomeCargo = dadosAtualizaCargoDTO.getNomeCargo() != null ? dadosAtualizaCargoDTO.getNomeCargo() : cargo.getNomeCargo();
        this.setor = cargo.getSetor();
        this.trabalhador = cargo.getTrabalhador();
    }

    public Cargo(long idCargo, String nomeCargo) {
        this.id = idCargo;
        this.nomeCargo = nomeCargo;
    }
}
