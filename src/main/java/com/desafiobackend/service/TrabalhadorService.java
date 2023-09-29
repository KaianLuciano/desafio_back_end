package com.desafiobackend.service;

import com.desafiobackend.exception.CpfDuplicadoException;
import com.desafiobackend.exception.DadoNaoEncontradoException;
import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Trabalhador;
import com.desafiobackend.model.dto.trabalhador.DadosAtualizaTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosCadastroTrabalhadorDTO;
import com.desafiobackend.model.dto.trabalhador.DadosListagemTrabalhadorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.TrabalhadorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrabalhadorService {

    private final TrabalhadorRepository trabalhadorRepository;
    private final CargoRepository cargoRepository;

    public List<DadosListagemTrabalhadorDTO> findAll() {
        List<Trabalhador> trabalhadores = trabalhadorRepository.findAll();
        return trabalhadores.stream().map(trabalhador -> new DadosListagemTrabalhadorDTO(trabalhador)).collect(Collectors.toList());
    }

    public DadosListagemTrabalhadorDTO findById(Long idTrabalhador) {
        Trabalhador trabalhador = trabalhadorRepository.findById(idTrabalhador).orElseThrow(() -> new DadoNaoEncontradoException("Trabalhador com o ID " + idTrabalhador + " não existente"));
        return new DadosListagemTrabalhadorDTO(trabalhador);
    }

    public DadosListagemTrabalhadorDTO save(Long idCargo, DadosCadastroTrabalhadorDTO dadosCadastroTrabalhador) {
        if(trabalhadorRepository.existsByCpf(dadosCadastroTrabalhador.getCpf())){throw new CpfDuplicadoException();}
        Cargo cargo = cargoRepository.findById(idCargo).orElseThrow(() -> new DadoNaoEncontradoException("Cargo com o ID " + idCargo + " não existente"));

        Trabalhador trabalhador = new Trabalhador(dadosCadastroTrabalhador);

        trabalhador.setCargo(cargo);
        Trabalhador trabalhadorSalvo = trabalhadorRepository.save(trabalhador);

        cargo.setTrabalhador(trabalhador);
        cargoRepository.save(cargo);

        return new DadosListagemTrabalhadorDTO(trabalhadorSalvo);
    }

    public DadosListagemTrabalhadorDTO update(Long idTrabalhador, DadosAtualizaTrabalhadorDTO dadosAtualizaTrabalhador) {
        Trabalhador trabalhador = trabalhadorRepository.findById(idTrabalhador).orElseThrow(() -> new DadoNaoEncontradoException("Trabalhador com o ID " + idTrabalhador + " não existente"));
        Trabalhador trabalhadorSalvo = trabalhadorRepository.save(new Trabalhador(trabalhador, dadosAtualizaTrabalhador));
        return new DadosListagemTrabalhadorDTO(trabalhadorSalvo);
    }
}
