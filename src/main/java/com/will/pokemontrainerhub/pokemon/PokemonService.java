package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import com.will.pokemontrainerhub.Exceptions.TrainerNotFound;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Pokemon> getPokemonById(Long id) throws PokemonNotFound {
        Optional<Pokemon> pokemonWithName = pokemonRepository.findById(id);
        if (pokemonWithName.isPresent()){
            return pokemonRepository.findById(id);
        } else {
            throw new PokemonNotFound("pokemon with id " + id + " not found");
        }
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonRepository.save(pokemon);
    }

    public void deletePokemonById(Long id) {
        pokemonRepository.deleteById(id);
    }

    public void updatePokemonById(Long id, Pokemon pokemon) throws PokemonNotFound {
        Optional<Pokemon> pokemonById = pokemonRepository.findById(id);
        if (pokemonById.isPresent()){
            Pokemon updatePokemon = pokemonById.get();
            updatePokemon.setName(pokemon.getName());
            updatePokemon.setHeight(pokemon.getHeight());
            updatePokemon.setWeight(pokemon.getWeight());
            updatePokemon.setLevel(pokemon.getLevel());
            updatePokemon.setTrainerId(pokemon.getTrainerId());
            updatePokemon.setGender(pokemon.getGender());
            pokemonRepository.save(updatePokemon);
        } else {
            throw new PokemonNotFound("pokemon with id " + id + " not found");
        }
    }
}
