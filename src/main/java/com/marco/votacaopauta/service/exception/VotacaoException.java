package com.marco.votacaopauta.service.exception;

public class VotacaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public VotacaoException(String msg){
        super(msg);
    }

    public VotacaoException(String msg, Throwable cause){
        super(msg, cause);
    }

}