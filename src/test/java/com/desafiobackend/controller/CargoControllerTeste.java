package com.desafiobackend.controller;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.cargo.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosListagemCargoDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        cargo = new Cargo(1L, "Nome Cargo");
        trabalhador = new Trabalhador(1L, "Nome Trabalhador", "12345678901");

        trabalhador.setCargo(cargo);
        cargo.setTrabalhador(trabalhador);
        cargo.setSetor(setor);

        List<Cargo> cargos = new ArrayList<>();
        cargos.add(cargo);
        setor.setCargos(cargos);
    }

    @Test
    void findAllTeste() throws Exception {
        when(cargoRepository.findAll()).thenReturn(List.of(cargo));

        mockMvc.perform(get("/cargo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(new DadosListagemCargoDTO(cargo)))));
    }

    @Test
    void findByIdTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));

        mockMvc.perform(get("/cargo/{idCargo}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemCargoDTO(cargo))));
    }

    @Test
    void findByIdNotFoundTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cargo/{idCargo}", 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTeste() throws Exception {
        when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));
        when(cargoRepository.save(Mockito.any())).thenReturn(cargo);

        mockMvc.perform(post("/cargo/{idSetor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroCargoDTO(cargo.getNomeCargo())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemCargoDTO(cargo))));
    }

    @Test
    void saveNotFoundTeste() throws Exception {
        when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(post("/cargo/{idSetor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroCargoDTO(cargo.getNomeCargo())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void saveCargoDuplicadoExceptionTeste() throws Exception {
        when(cargoRepository.existsByNomeCargoEqualsIgnoreCase(cargo.getNomeCargo())).thenReturn(true);

        mockMvc.perform(post("/cargo/{idSetor}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosCadastroCargoDTO(cargo.getNomeCargo())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void updateTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        when(cargoRepository.save(Mockito.any())).thenReturn(cargo);

        mockMvc.perform(put("/cargo/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaCargoDTO("Nome Atualizado")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemCargoDTO(cargo))));
    }

    @Test
    void updateNotFoundTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/cargo/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaCargoDTO("Nome Atualizado")))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCargoDuplicadoExceptionTeste() throws Exception {
        when(cargoRepository.existsByNomeCargoEqualsIgnoreCase(cargo.getNomeCargo())).thenReturn(true);

        mockMvc.perform(put("/cargo/{idCargo}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new DadosAtualizaCargoDTO(cargo.getNomeCargo())))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    void deleteTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));

        mockMvc.perform(delete("/cargo/{idSetor}/{idCargo}", 1L, 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(new DadosListagemCargoDTO(cargo))));
    }

    @Test
    void deleteSetorNaoPossuiCargoExceptionTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.of(cargo));
        setor.setCargos(List.of(new Cargo(1L, "Nome Diferente")));
        when(setorRepository.findById(1L)).thenReturn(Optional.of(setor));

        mockMvc.perform(delete("/cargo/{idSetor}/{idCargo}", 1L, 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNotFoundCargoTeste() throws Exception {
        when(cargoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/cargo/{idSetor}/{idCargo}", 1L, 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNotFoundSetorTeste() throws Exception {
        when(setorRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/cargo/{idSetor}/{idCargo}", 1L, 1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
