package com.desafiobackend.exception;

import lombok.Getter;

@Getter
public class DadoNaoEncontradoException extends RuntimeException {

    private String msg;

    public DadoNaoEncontradoException(String msg) {
        this.msg = msg;
    }
}
