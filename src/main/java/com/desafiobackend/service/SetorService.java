package com.desafiobackend.service;

import com.desafiobackend.exception.CargoDuplicadoException;
import com.desafiobackend.exception.DadoNaoEncontradoException;
import com.desafiobackend.exception.SetorDuplicadoException;
import com.desafiobackend.model.Cargo;
import com.desafiobackend.model.Setor;
import com.desafiobackend.model.dto.DadosAtualizaSetorDTO;
import com.desafiobackend.model.dto.DadosCadastraSetorDTO;
import com.desafiobackend.model.dto.DadosCadastroCargoDTO;
import com.desafiobackend.repository.CargoRepository;
import com.desafiobackend.repository.SetorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorRepository setorRepository;
    private final CargoRepository cargoRepository;

    public List<Setor> findAll() {
        return setorRepository.findAll();
    }

    public Setor findById(Long idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        return setor;
    }

    public Setor save(DadosCadastraSetorDTO dadosCadastraSetorDTO) {
        if (setorRepository.existsByNomeSetor(dadosCadastraSetorDTO.getNomeSetor())) {throw new SetorDuplicadoException();}

        dadosCadastraSetorDTO.getCargos().stream().forEach(cargo -> {
            if(cargoRepository.existsByNomeCargo(cargo.getNomeCargo())) {throw new CargoDuplicadoException();}
        });

        Setor setor = new Setor(dadosCadastraSetorDTO);

        List<Cargo> cargos = new ArrayList<>();
        for(DadosCadastroCargoDTO dadosCadastroCargoDTO :dadosCadastraSetorDTO.getCargos()) {
            if(dadosCadastroCargoDTO.getNomeCargo() != "") {
                cargos.add(new Cargo(dadosCadastroCargoDTO));
            }
        }

        setor.setCargos(cargos);
        Setor setorSalvo = setorRepository.save(setor);

        return setorSalvo;
    }

    public Setor update(DadosAtualizaSetorDTO dadosAtualizaSetor, Long idSetor) {
        if (setorRepository.existsByNomeSetor(dadosAtualizaSetor.getNomeSetor())) {
            throw new SetorDuplicadoException();
        }

        Setor setorEncontrado = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));

        Setor setor = new Setor(dadosAtualizaSetor, setorEncontrado);
        setorRepository.save(setor);
        return setor;
    }

    public Setor delete(Long idSetor) {
        Setor setor = setorRepository.findById(idSetor).orElseThrow(() -> new DadoNaoEncontradoException("Setor com o ID " + idSetor + " não existente"));
        setorRepository.delete(setor);
        return setor;
    }
}
