package com.desafiobackend.controller;

import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.setor.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.setor.DadosCadastraSetorDTO;
import com.desafiobackend.model.dto.setor.DadosListagemSetorDTO;
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
    public ResponseEntity<List<DadosListagemSetorDTO>> findAll() {
        return ResponseEntity.ok().body(setorService.findAll());
    }

    @Operation(summary = "Procura o setor que representa o id fornecido")
    @GetMapping("/{idSetor}")
    public ResponseEntity<DadosListagemSetorDTO> findById(@PathVariable(value = "idSetor") Long idSetor) {
        return ResponseEntity.ok().body(setorService.findById(idSetor));
    }

    @Operation(summary = "Salva o setor fornecido")
    @PostMapping
    public ResponseEntity<DadosListagemSetorDTO> save(@RequestBody DadosCadastraSetorDTO dadosCadastraSetorDTO) {
        return ResponseEntity.ok().body(setorService.save(dadosCadastraSetorDTO));
    }

    @Operation(summary = "Atualiza o setor que representa o ID fornecido")
    @PutMapping("/{idSetor}")
    public ResponseEntity<DadosListagemSetorDTO> update(@PathVariable(value = "idSetor") Long idSetor, @RequestBody DadosAtualizaSetorDTO dadosAtualizaSetorDTO) {
        return ResponseEntity.ok().body(setorService.update(dadosAtualizaSetorDTO, idSetor));
    }

    @Operation(summary = "Deleta o setor que representa o id fornecido")
    @DeleteMapping("/{idSetor}/{idCargo}")
    public ResponseEntity<DadosListagemSetorDTO> delete(@PathVariable(value = "idSetor") Long idSetor) {
        return ResponseEntity.ok().body(setorService.delete(idSetor));
    }

}
