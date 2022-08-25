package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

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

    public void addPokemon(PokemonReqBody pokemon) {
        pokemonRepository.save(new Pokemon(pokemon.getName(), pokemon.getGender(), pokemon.getLevel(), pokemon.getHeight(), pokemon.getWeight()));
    }

    public void deletePokemonById(Long id) {
        Optional<Pokemon> pokemonWithName = pokemonRepository.findById(id);
        if (pokemonWithName.isPresent()){
            pokemonRepository.deleteById(id);
        } else {
            throw new PokemonNotFound("pokemon with id " + id + " not found");
        }
    }

    public void updatePokemonById(Long id, PokemonReqBody pokemon) throws PokemonNotFound {
        Optional<Pokemon> pokemonById = pokemonRepository.findById(id);
        if (pokemonById.isPresent()){
            Pokemon updatePokemon = pokemonById.get();
            updatePokemon.setName(pokemon.getName());
            updatePokemon.setGender(pokemon.getGender());
            updatePokemon.setLevel(pokemon.getLevel());
            updatePokemon.setHeight(pokemon.getHeight());
            updatePokemon.setWeight(pokemon.getWeight());
            pokemonRepository.save(updatePokemon);
        } else {
            throw new PokemonNotFound("pokemon with id " + id + " not found");
        }
    }
}
