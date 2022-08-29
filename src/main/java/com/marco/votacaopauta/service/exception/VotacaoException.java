package com.marco.votacaopauta.service.exception;

import java.io.Serial;

public class VotacaoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public VotacaoException(String msg){
        super(msg);
    }

    public VotacaoException(String msg, Throwable cause){
        super(msg, cause);
    }

}