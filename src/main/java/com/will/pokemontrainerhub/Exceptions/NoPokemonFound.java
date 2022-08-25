package com.will.pokemontrainerhub.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class NoPokemonFound extends IllegalStateException {
    public NoPokemonFound(String message) {
        super(message);
    }
}
