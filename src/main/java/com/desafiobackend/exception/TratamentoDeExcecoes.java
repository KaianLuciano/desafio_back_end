package com.desafiobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratamentoDeExcecoes {

    @ExceptionHandler(SetorDuplicadoException.class)
    public ResponseEntity<String> setorComNomeDuplicado() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Nome do setor fornecido existente");
    }
}
