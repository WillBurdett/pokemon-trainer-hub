package com.will.pokemontrainerhub.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class TrainerNotFound extends Throwable {
    public TrainerNotFound(String message) {
        super(message);
    }
}
