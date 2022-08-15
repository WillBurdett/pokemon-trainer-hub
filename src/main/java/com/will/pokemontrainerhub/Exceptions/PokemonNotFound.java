package com.will.pokemontrainerhub.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST)
public class PokemonNotFound extends IllegalStateException {
    public PokemonNotFound(String message) {
        super(message);
    }
}
