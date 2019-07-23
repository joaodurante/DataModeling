package com.joaodurante.springfirst.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> errors;

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
        this.errors = new ArrayList<>();
    }

    public List<FieldMessage> getErrors() {
        return this.errors;
    }

    public void addError(String field, String message){
        this.errors.add(new FieldMessage(field, message));
    }
}
