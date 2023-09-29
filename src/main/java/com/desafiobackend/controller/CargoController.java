package com.desafiobackend.controller;

import com.desafiobackend.model.dto.cargo.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosListagemCargoDTO;
import com.desafiobackend.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@AllArgsConstructor
@Tag(name = "Cargo")
public class CargoController {

    private final CargoService cargoService;

    @Operation(summary = "Lista todos os cargos")
    @GetMapping
    public ResponseEntity<List<DadosListagemCargoDTO>> findAll() {
        return ResponseEntity.ok().body(cargoService.findAll());
    }

    @Operation(summary = "Procura o cargo que representa o id fornecido")
    @GetMapping("/{idCargo}")
    public ResponseEntity<DadosListagemCargoDTO> findById(@PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(cargoService.findById(idCargo));
    }

    @Operation(summary = "Salva o cargo no setor que representa o ID fornecido")
    @PostMapping("/{idSetor}")
    public ResponseEntity<DadosListagemCargoDTO> save(@PathVariable(value = "idSetor") Long idSetor, @RequestBody DadosCadastroCargoDTO dadosCadastroCargoDTO) {
        return ResponseEntity.ok().body(cargoService.save(dadosCadastroCargoDTO, idSetor));
    }

    @Operation(summary = "Atualiza o cargo que representa o ID fornecido")
    @PutMapping("/{idCargo}")
    public ResponseEntity<DadosListagemCargoDTO> update(@PathVariable(value = "idCargo") Long idCargo, DadosAtualizaCargoDTO dadosAtualizaCargoDTO) {
        return ResponseEntity.ok().body(cargoService.update(idCargo, dadosAtualizaCargoDTO));
    }

    @Operation(summary = "Deleta o cargo que representa o idCargo, do setor que representa idSetor")
    @DeleteMapping("/{idSetor}/{idCargo}")
    public ResponseEntity<DadosListagemCargoDTO> deleteCargo(@PathVariable(value = "idSetor") Long idSetor, @PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(cargoService.deleteCargo(idSetor, idCargo));
    }
}
