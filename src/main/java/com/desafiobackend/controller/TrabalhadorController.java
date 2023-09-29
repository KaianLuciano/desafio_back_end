package com.desafiobackend.controller;

import com.desafiobackend.model.dto.trabalhador.DadosAtualizaTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosCadastroTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosListagemTrabalhadorDTO;
import com.desafiobackend.service.TrabalhadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabalhador")
@AllArgsConstructor
@Tag(name = "Trabalhador")
public class TrabalhadorController {

    private final TrabalhadorService trabalhadorService;

    @Operation(summary = "Lista todos os trabalhadores")
    @GetMapping
    public ResponseEntity<List<DadosListagemTrabalhadorDTO>> findAll() {
        return ResponseEntity.ok().body(trabalhadorService.findAll());
    }

    @Operation(summary = "Procura o trabalhador que representa o ID fornecido")
    @GetMapping("/{idTrabalhador}")
    public ResponseEntity<DadosListagemTrabalhadorDTO> findById(@PathVariable(value = "idTrabalhador") Long idTrabalhador) {
        return ResponseEntity.ok().body(trabalhadorService.findById(idTrabalhador));
    }

    @Operation(summary = "Salva o trabalhador e atrela o mesmo ao cargo que correponde ao ID fornecido.")
    @PostMapping("/{idCargo}")
    public ResponseEntity<DadosListagemTrabalhadorDTO> save(@PathVariable(value = "idCargo") Long idCargo, @RequestBody DadosCadastroTrabalhadorDTO dadosCadastroTrabalhadorDTO) {
        return ResponseEntity.ok().body(trabalhadorService.save(idCargo, dadosCadastroTrabalhadorDTO));
    }

    @Operation(summary = "Atualiza o trabalhador que representa o ID fornecido")
    @PutMapping("/{idTrabalhador}")
    public ResponseEntity<DadosListagemTrabalhadorDTO> update(@PathVariable(value = "idTrabalhador") Long idTrabalhador, @RequestBody DadosAtualizaTrabalhadorDTO dadosAtualizaTrabalhadorDTO) {
        return ResponseEntity.ok().body(trabalhadorService.update(idTrabalhador, dadosAtualizaTrabalhadorDTO));
    }

    @Operation(summary = "Deleta o trabalhador que representa o ID fornecido")
    @DeleteMapping("/{idTrabalhador}/{idCargo}")
    public ResponseEntity<DadosListagemTrabalhadorDTO> delete(@PathVariable(value = "idTrabalhador") Long idTrabalhador, @PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(trabalhadorService.delete(idTrabalhador, idCargo));
    }

 }
