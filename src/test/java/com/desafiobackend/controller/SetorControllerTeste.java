package com.desafiobackend.controller;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.model.dto.setor.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.setor.DadosCadastraSetorDTO;
import com.desafiobackend.model.dto.setor.DadosListagemSetorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
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

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SetorControllerTeste {

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

    @AfterEach
    public void cleanUp() {
        setor = null;
        cargo = null;
        trabalhador = null;
    }

    @Test
    void findAllTeste() throws Exception {
        Mockito.when(setorRepository.findAll()).thenReturn(List.of(setor));

        mockMvc.perform(get("/setor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(new DadosListagemSetorDTO(setor)))));
    }

    @Test
    void findByIdTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));

        mockMvc.perform(get("/setor/{idSetor}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemSetorDTO(setor))));
    }

    @Test
    void findByIdNotFoundTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/setor/{idSetor}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTeste() throws Exception {
        Mockito.when(setorRepository.save(Mockito.any())).thenReturn(setor);

        mockMvc.perform(post("/setor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastraSetorDTO(setor.getNomeSetor(), List.of(new DadosCadastroCargoDTO(cargo.getNomeCargo())))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemSetorDTO(setor))));
    }

    @Test
    void saveSetorDuplicadoExceptionTeste() throws Exception {
        Mockito.when(setorRepository.existsByNomeSetor(setor.getNomeSetor())).thenReturn(true);

        mockMvc.perform(post("/setor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastraSetorDTO(setor.getNomeSetor(), List.of(new DadosCadastroCargoDTO(cargo.getNomeCargo())))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void saveSetorCargoDuplicadoExceptionTeste() throws Exception {
        Mockito.when(cargoRepository.existsByNomeCargoEqualsIgnoreCase(cargo.getNomeCargo())).thenReturn(true);

        mockMvc.perform(post("/setor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastraSetorDTO(setor.getNomeSetor(), List.of(new DadosCadastroCargoDTO(cargo.getNomeCargo())))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void updateTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));
        setor.setNomeSetor("Nome Atualizado");

        mockMvc.perform(put("/setor/{idSetor}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new DadosAtualizaSetorDTO("Nome Atualizado")))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemSetorDTO(setor))));
    }

    @Test
    void updateSetorDuplicadoExceptionTeste() throws Exception {
        Mockito.when(setorRepository.existsByNomeSetor("Nome Atualizado")).thenReturn(true);

        mockMvc.perform(put("/setor/{idSetor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaSetorDTO("Nome Atualizado")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void updateNotFoundTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/setor/{idSetor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaSetorDTO("Nome Atualizado")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));

        mockMvc.perform(delete("/setor/{idSetor}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemSetorDTO(setor))));

        verify(setorRepository).delete(setor);
    }

    @Test
    void deleteNotFoundTeste() throws Exception {
        Mockito.when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/setor/{idSetor}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
