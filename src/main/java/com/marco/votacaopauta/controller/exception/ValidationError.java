package com.marco.votacaopauta.controller.exception;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    @Serial
    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors  = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fildName, String message) {
        errors.add(new FieldMessage(fildName, message));
    }
}
