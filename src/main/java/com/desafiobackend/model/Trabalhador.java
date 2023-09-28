package com.desafiobackend.model;

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
    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Setor setor;
    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;
    private String nome;
    @Column(unique = true, nullable = true, length = 11)
    private String cpf;
}
