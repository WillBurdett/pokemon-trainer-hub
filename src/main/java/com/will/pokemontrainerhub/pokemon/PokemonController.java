package com.will.pokemontrainerhub.pokemon;

import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService){
        this.pokemonService = pokemonService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Pokemon> getAllPokemon(){
        return pokemonService.getAllPokemon();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Pokemon> getPokemonById(@PathVariable Long id) throws PokemonNotFound {
        return pokemonService.getPokemonById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addPokemon(@RequestBody PokemonReqBody pokemon){
        pokemonService.addPokemon(pokemon);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePokemonById(@PathVariable Long id){
        pokemonService.deletePokemonById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updatePokemon(@PathVariable Long id, @RequestBody PokemonReqBody pokemon) throws PokemonNotFound {
        pokemonService.updatePokemonById(id, pokemon);
    }
}
