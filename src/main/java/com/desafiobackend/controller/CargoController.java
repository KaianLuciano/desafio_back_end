package com.desafiobackend.controller;

import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.cargo.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@AllArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @Operation(summary = "Lista todos os cargos")
    @GetMapping
    public ResponseEntity<List<Cargo>> findAll() {
        return ResponseEntity.ok().body(cargoService.findAll());
    }

    @Operation(summary = "Procura o cargo que representa o id fornecido")
    @GetMapping("/{idCargo}")
    public ResponseEntity<Cargo> findById(@PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(cargoService.findById(idCargo));
    }

    @Operation(summary = "Salva o cargo no setor que representa o ID fornecido")
    @PostMapping("/{idSetor}")
    public ResponseEntity<Cargo> save(@PathVariable(value = "idSetor") Long idSetor, @RequestBody DadosCadastroCargoDTO dadosCadastroCargoDTO) {
        return ResponseEntity.ok().body(cargoService.save(dadosCadastroCargoDTO, idSetor));
    }

    @Operation(summary = "Atualiza o cargo que representa o ID fornecido")
    @PutMapping("/{idCargo}")
    public ResponseEntity<Cargo> update(@PathVariable(value = "idCargo") Long idCargo, DadosAtualizaCargoDTO dadosAtualizaCargoDTO) {
        return ResponseEntity.ok().body(cargoService.update(idCargo, dadosAtualizaCargoDTO));
    }

    @Operation(summary = "Deleta o cargo que representa o idCargo, do setor que representa idSetor")
    @DeleteMapping("/{idSetor}/{idCargo}")
    public ResponseEntity<Setor> deleteCargo(@PathVariable(value = "idSetor") Long idSetor, @PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(cargoService.deleteCargo(idSetor, idCargo));
    }
}
