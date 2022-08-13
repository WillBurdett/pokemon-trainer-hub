package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    PokemonService(PokemonRepository pokemonRepository){
        this.pokemonRepository = pokemonRepository;
    }
    public List<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }
}
