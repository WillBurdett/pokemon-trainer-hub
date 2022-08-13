package com.will.pokemontrainerhub.trainer;

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
    public void addTrainer(@RequestBody Trainer Trainer){
        trainerService.addTrainer(Trainer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTrainerById(@PathVariable Long id){
        trainerService.deleteTrainerById(id);
    }
    // TODO: 13/08/2022 Prevent pokemon being deleted when trainers are deleted (check cascade on trainer model) 

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateTrainer(@PathVariable Long id, @RequestBody Trainer Trainer) throws TrainerNotFound {
        trainerService.updateTrainerById(id, Trainer);
    }
}
