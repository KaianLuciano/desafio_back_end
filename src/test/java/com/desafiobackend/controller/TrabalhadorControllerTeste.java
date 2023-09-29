package com.desafiobackend.controller;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.trabalhador.DadosAtualizaTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosCadastroTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosListagemTrabalhadorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import com.desafiobackend.repository.TrabalhadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrabalhadorControllerTeste {

    @MockBean
    private SetorRepository setorRepository;
    @MockBean
    private CargoRepository cargoRepository;
    @MockBean
    private TrabalhadorRepository trabalhadorRepository;
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

        cargo.setSetor(setor);
        trabalhador.setCargo(cargo);
        cargo.setTrabalhador(trabalhador);
        setor.setCargos(List.of(cargo));
    }

    @AfterEach
    public void cleanUp() {
        setor = null;
        cargo = null;
        trabalhador = null;
    }

    @Test
    void findAllTeste() throws Exception {
        when(trabalhadorRepository.findAll()).thenReturn(List.of(trabalhador));

        mockMvc.perform(get("/trabalhador"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(new DadosListagemTrabalhadorDTO(trabalhador)))));
    }

    @Test
    void findByIdTeste() throws Exception {
        when(trabalhadorRepository.findById(1L)).thenReturn(Optional.of(trabalhador));

        mockMvc.perform(get("/trabalhador/{idTrabalhador}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemTrabalhadorDTO(trabalhador))));
    }

    @Test
    void findByIdNotFoundTeste() throws Exception {
        when(trabalhadorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/trabalhador/{idTrabalhador}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        when(trabalhadorRepository.save(Mockito.any())).thenReturn(trabalhador);

        mockMvc.perform(post("/trabalhador/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroTrabalhadorDTO(trabalhador.getNome(), trabalhador.getCpf())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemTrabalhadorDTO(trabalhador))));
    }

    @Test
    void saveNotFoundTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/trabalhador/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroTrabalhadorDTO(trabalhador.getNome(), trabalhador.getCpf())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveCpfDuplicadoExceptionTeste() throws Exception {
        when(trabalhadorRepository.existsByCpf(trabalhador.getCpf())).thenReturn(true);

        mockMvc.perform(post("/trabalhador/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroTrabalhadorDTO(trabalhador.getNome(), trabalhador.getCpf())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void updateteste() throws Exception {
        when(trabalhadorRepository.findById(1L)).thenReturn(Optional.of(trabalhador));
        when(trabalhadorRepository.save(Mockito.any())).thenReturn(trabalhador);
        trabalhador.setNome("Nome Atualizado");
        trabalhador.setCpf("1233445");

        mockMvc.perform(put("/trabalhador/{idTrabalhador}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaTrabalhadorDTO("Nome Atualizado", "1233445")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemTrabalhadorDTO(trabalhador))));
    }

    @Test
    void updateNotFoundTeste() throws Exception {
        when(trabalhadorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/trabalhador/{idTrabalhador}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaTrabalhadorDTO("Nome Atualizado", "1233445")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
