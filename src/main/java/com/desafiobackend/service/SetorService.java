package com.desafiobackend.service;

import com.desafiobackend.exception.CargoDuplicadoException;
import com.desafiobackend.exception.DadoNaoEncontradoException;
import com.desafiobackend.exception.SetorDuplicadoException;
import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.setor.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.setor.DadosCadastraSetorDTO;
import com.desafiobackend.model.dto.setor.DadosListagemSetorDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;
    private final CargoRepository cargoRepository;

    public List<DadosListagemSetorDTO> findAll() {
        List<Setor> setores = setorRepository.findAll();
        return setores.stream().map(setor -> new DadosListagemSetorDTO(setor)).collect(Collectors.toList());
    }

    public DadosListagemSetorDTO findById(Long idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        return new DadosListagemSetorDTO(setor);
    }

    public DadosListagemSetorDTO save(DadosCadastraSetorDTO dadosCadastraSetorDTO) {
        if (setorRepository.existsByNomeSetor(dadosCadastraSetorDTO.getNomeSetor())) {
            throw new SetorDuplicadoException();
        }
        dadosCadastraSetorDTO.getCargos().stream().forEach(cargo -> {
            if(cargoRepository.existsByNomeCargo(cargo.getNomeCargo())) {throw new CargoDuplicadoException();}
        });

        Setor setor = new Setor(dadosCadastraSetorDTO);
        setorRepository.save(setor);

        List<Cargo> cargos = dadosCadastraSetorDTO.getCargos().stream().map(dadosCadastroCargo -> new Cargo(dadosCadastroCargo)).collect(Collectors.toList());
        for(Cargo cargo : cargos) {
            cargo.setSetor(setor);
            cargoRepository.save(cargo);
        }

        setor.setCargos(cargos);
        Setor setorSalvo = setorRepository.save(setor);

        return new DadosListagemSetorDTO(setorSalvo);
    }

    public DadosListagemSetorDTO update(DadosAtualizaSetorDTO dadosAtualizaSetor, Long idSetor) {
        if (setorRepository.existsByNomeSetor(dadosAtualizaSetor.getNomeSetor())) {
            throw new SetorDuplicadoException();
        }

        Setor setorEncontrado = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));

        Setor setor = new Setor(dadosAtualizaSetor, setorEncontrado);
        setorRepository.save(setor);
        return new DadosListagemSetorDTO(setor);
    }

    public DadosListagemSetorDTO delete(Long idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        setorRepository.delete(setor);
        return new DadosListagemSetorDTO(setor);
    }
}
