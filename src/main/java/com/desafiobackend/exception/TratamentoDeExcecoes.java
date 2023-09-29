package com.desafiobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeExcecoes {

    @ExceptionHandler(SetorDuplicadoException.class)
    public ResponseEntity<String> setorComNomeDuplicado() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome do setor fornecido duplicado já existe na base de dados");
    }

    @ExceptionHandler(DadoNaoEncontradoException.class)
    public ResponseEntity<String> dadoNaoEncontrado(DadoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMsg());
    }

    @ExceptionHandler(CargoDuplicadoException.class)
    public ResponseEntity<String> cargoDuplicado() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome do cargo fornecido já existe na base de dados");
    }

    @ExceptionHandler(CpfDuplicadoException.class)
    public ResponseEntity<String> cpfDuplicado() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Cpf fornecido já existe na base de dados");
    }

    @ExceptionHandler(SetorNaoPossuiCargoException.class)
    public ResponseEntity<String> setorNaoPossuiCargo() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo correspondente ao idCargo, não existe no setor que representa idSetor");
    }

    @ExceptionHandler(CargoNaoPossuiTrabalhadorException.class)
    public ResponseEntity<String> cargoNaoPossuiTrabalhador() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabalhador correspondente ao idTrabalhador, não existe no cargo que representa idCargo");
    }
}
