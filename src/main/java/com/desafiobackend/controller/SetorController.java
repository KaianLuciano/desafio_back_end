package com.desafiobackend.controller;

import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.DadosCadastraSetorDTO;
import com.desafiobackend.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/setor")
@AllArgsConstructor
public class SetorController {

    private final SetorService setorService;

    @Operation(summary = "Lista todos os setores")
    @GetMapping
    public ResponseEntity<List<Setor>> findAll() {
        return ResponseEntity.ok().body(setorService.findAll());
    }

    @Operation(summary = "Procura o setor que representa o id fornecido")
    @GetMapping("/{idSetor}")
    public ResponseEntity<Setor> findById(@PathVariable(value = "idSetor") Long idSetor) {
        return ResponseEntity.ok().body(setorService.findById(idSetor));
    }

    @Operation(summary = "Salva o setor fornecido")
    @PostMapping
    public ResponseEntity<Setor> save(@RequestBody DadosCadastraSetorDTO dadosCadastraSetorDTO) {
        return ResponseEntity.ok().body(setorService.save(dadosCadastraSetorDTO));
    }

    @Operation(summary = "Atualiza o setor que representa o ID fornecido")
    @PutMapping("/{idSetor}")
    public ResponseEntity<Setor> update(@PathVariable(value = "idSetor") Long idSetor, @RequestBody DadosAtualizaSetorDTO dadosAtualizaSetorDTO) {
        return ResponseEntity.ok().body(setorService.update(dadosAtualizaSetorDTO, idSetor));
    }

    @Operation(summary = "Deleta o cargo que representa o idCargo, do setor que representa idSetor")
    @DeleteMapping("/{idSetor}/{idCargo}")
    public ResponseEntity<Setor> deleteCargos(@PathVariable(value = "idSetor") Long idSetor, @PathVariable(value = "idCargo") Long idCargo) {
        return ResponseEntity.ok().body(setorService.deleteCargo(idSetor, idCargo));
    }

}
