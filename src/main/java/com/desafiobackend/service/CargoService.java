package com.desafiobackend.service;

import com.desafiobackend.exception.CargoDuplicadoException;
import com.desafiobackend.exception.DadoNaoEncontradoException;
import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.cargo.DadosAtualizaCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosCadastroCargoDTO;
import com.desafiobackend.model.dto.cargo.DadosListagemCargoDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final SetorRepository setorRepository;

    public List<DadosListagemCargoDTO> findAll() {
        List<Cargo> cargos = cargoRepository.findAll();
        return cargos.stream().map(cargo -> new DadosListagemCargoDTO(cargo)).collect(Collectors.toList());
    }

    public DadosListagemCargoDTO findById(Long idCargo) {
        Cargo cargo = cargoRepository.findById(idCargo).orElseThrow(() -> new DadoNaoEncontradoException("Cargo com o ID " + idCargo + " não existente"));
        return new DadosListagemCargoDTO(cargo);
    }

    public DadosListagemCargoDTO save(DadosCadastroCargoDTO dadosCadastroCargoDTO, Long idSetor) {
        if(cargoRepository.existsByNomeCargo(dadosCadastroCargoDTO.getNomeCargo())) {
            throw new CargoDuplicadoException();
        }

        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        Cargo cargo = new Cargo(dadosCadastroCargoDTO);

        cargo.setSetor(setor);
        Cargo cargoSalvo = cargoRepository.save(cargo);

        return new DadosListagemCargoDTO(cargoSalvo);
    }

    public DadosListagemCargoDTO update(Long idCargo, DadosAtualizaCargoDTO dadosAtualizaCargoDTO) {
        Cargo cargoEncontrado = cargoRepository.findById(idCargo).orElseThrow(() -> new DadoNaoEncontradoException("Cargo com o ID " + idCargo + " não existente"));
        Cargo cargo = new Cargo(cargoEncontrado, dadosAtualizaCargoDTO);

        Cargo cargoSalvo = cargoRepository.save(cargo);

        return new DadosListagemCargoDTO(cargoSalvo);
    }

    public DadosListagemCargoDTO deleteCargo(Long idSetor, Long idCargo) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        Cargo cargo = cargoRepository.findById(idCargo).orElseThrow(() -> new DadoNaoEncontradoException("Cargo com o ID " + idCargo + " não existente"));

        if (!setor.getCargos().contains(cargo)) {
            throw new NoSuchElementException("O cargo não pertence ao setor especificado.");
        }

        setor.getCargos().remove(cargo);
        setorRepository.save(setor);
        cargoRepository.delete(cargo);

        return new DadosListagemCargoDTO(cargo);
    }

}
