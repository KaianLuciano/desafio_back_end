package com.desafiobackend.service;

import com.desafiobackend.exception.SetorDuplicadoException;
import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.DadosCadastraSetorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;
    private final CargoRepository cargoRepository;

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    public Setor findById(Long idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new NoSuchElementException());
        return setor;
    }

    public Setor save(DadosCadastraSetorDTO dadosCadastraSetorDTO) {
        if (setorRepository.existsByNomeSetor(dadosCadastraSetorDTO.getNomeSetor())) {
            throw new SetorDuplicadoException("Setor com o mesmo nome já existe.");
        }

        Setor setor = new Setor(dadosCadastraSetorDTO);
        List<Cargo> cargos = dadosCadastraSetorDTO.getCargos().stream()
                .map(dadosCadastroCargoDTO -> new Cargo(dadosCadastroCargoDTO)).collect(Collectors.toList());

        setorRepository.save(setor);
        cargos.stream().forEach(cargo -> cargo.setSetor(setor));
        cargoRepository.saveAll(cargos);
        setor.setCargos(cargos);
        setorRepository.save(setor);

        return setor;
    }

    public Setor update(DadosAtualizaSetorDTO dadosAtualizaSetor, Long idSetor) {
        Setor setor = new Setor(dadosAtualizaSetor);
        setorRepository.save(setor);
        return setor;
    }

    public Setor deleteCargo(Long idSetor, Long idCargo) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new NoSuchElementException());
        Cargo cargo = cargoRepository.findById(idCargo).orElseThrow(() -> new NoSuchElementException());

        if (!setor.getCargos().contains(cargo)) {
            throw new NoSuchElementException("O cargo não pertence ao setor especificado.");
        }

        setor.getCargos().remove(cargo);
        setorRepository.save(setor);

        return setor;
    }
}
