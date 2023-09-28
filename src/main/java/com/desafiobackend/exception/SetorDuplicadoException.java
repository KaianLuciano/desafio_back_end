package com.desafiobackend.exception;

public class SetorDuplicadoException extends RuntimeException{

    private String msg;

    public SetorDuplicadoException(String msg) {
        this.msg = msg;
    }
}
