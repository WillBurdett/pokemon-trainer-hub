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
    public void addTrainer(@RequestBody TrainerReqBody trainer){
        trainerService.addTrainer(trainer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrainerById(@PathVariable Long id){
        trainerService.deleteTrainerById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateNonPokemonTrainerInfo(@PathVariable Long id, @RequestBody TrainerReqBody trainer) throws TrainerNotFound {
        trainerService.updateTrainerById(id, trainer);
    }

    @RequestMapping(value = "/{id}/add-pokemon/{allPokemonIds}", method = RequestMethod.PUT)
    public void addPokemonToTrainer(@PathVariable Long id, @PathVariable String allPokemonIds) throws TrainerNotFound, PokemonNotFound {
        trainerService.addPokemonToTrainer(id, allPokemonIds);
    }

    @RequestMapping(value = "/{id}/remove-pokemon/{allPokemonIds}", method = RequestMethod.PUT)
    public void removePokemonFromTrainer(@PathVariable Long id, @PathVariable String allPokemonIds) throws TrainerNotFound, PokemonNotFound {
        trainerService.removePokemonFromTrainer(id, allPokemonIds);
    }

    @RequestMapping(value = "/{id}/remove-all-pokemon", method = RequestMethod.PUT)
    public void removeAllPokemonFromTrainer(@PathVariable Long id) throws TrainerNotFound {
        trainerService.removeAllPokemonFromTrainer(id);
    }
}
