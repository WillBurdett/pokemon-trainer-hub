package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import com.will.pokemontrainerhub.Exceptions.TrainerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/trainer")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Trainer> getAllTrainers(){
        return trainerService.getAllTrainers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Trainer> getTrainerById(@PathVariable Long id) throws TrainerNotFound {
        return trainerService.getTrainerById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTrainer(@RequestBody Trainer trainer){
        trainerService.addTrainer(trainer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrainerById(@PathVariable Long id){
        trainerService.deleteTrainerById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateNonPokemonTrainerInfo(@PathVariable Long id, @RequestBody Trainer trainer) throws TrainerNotFound {
        trainerService.updateTrainerById(id, trainer);
    }

    @RequestMapping(value = "/{id}/add-pokemon/{pokemonId}", method = RequestMethod.PUT)
    public void addPokemonToTrainer(@PathVariable Long id, @PathVariable Long pokemonId) throws TrainerNotFound, PokemonNotFound {
        trainerService.addPokemonToTrainer(id, pokemonId);
    }
    @RequestMapping(value = "/{id}/add-multiple-pokemon/{allPokemonIds}", method = RequestMethod.PUT)
    public void addMultiplePokemonToTrainer(@PathVariable Long id, @PathVariable String allPokemonIds) throws TrainerNotFound, PokemonNotFound {
        trainerService.addMultiplePokemonToTrainer(id, allPokemonIds);
    }

    // TODO: 15/08/2022 Prevent pokemon being stolen from other trainers when add (if has trainer id, don't add)

    @RequestMapping(value = "/{id}/remove-pokemon/{pokemonId}", method = RequestMethod.PUT)
    public void deletePokemonFromTrainer(@PathVariable Long id, @PathVariable Long pokemonId) throws TrainerNotFound, PokemonNotFound {
        trainerService.deletePokemonFromTrainer(id, pokemonId);
    }

    @RequestMapping(value = "/{id}/remove-multiple-pokemon/{allPokemonIds}", method = RequestMethod.PUT)
    public void removeMultiplePokemonToTrainer(@PathVariable Long id, @PathVariable String allPokemonIds) throws TrainerNotFound, PokemonNotFound {
        trainerService.removeMultiplePokemonFromTrainer(id, allPokemonIds);
    }

    // TODO: 15/08/2022 Add a feature to remove all pokemon from a trainer
}
