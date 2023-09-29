package com.desafiobackend.controller;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.model.dto.setor.DadosCadastraSetorDTO;
import com.desafiobackend.model.dto.setor.DadosListagemSetorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@SpringBootTest
@AutoConfigureMockMvc
public class CargoControllerTeste {

    @MockBean
    private SetorRepository setorRepository;
    @MockBean
    private CargoRepository cargoRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Setor setor;
    private Cargo cargo;
    private Trabalhador trabalhador;

    @BeforeEach
    public void setUp() {
        setor = new Setor(1L, "Nome Setor");
        cargo = new Cargo(1L, "NomeCargo");
        trabalhador = new Trabalhador(1L, "Nome Trabalhador", "12345678901");

        trabalhador.setCargo(cargo);
        cargo.setTrabalhador(trabalhador);
        setor.setCargos(List.of(cargo));
    }

}
