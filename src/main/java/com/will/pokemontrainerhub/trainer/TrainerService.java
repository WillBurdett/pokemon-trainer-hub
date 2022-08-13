package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Exceptions.TrainerNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    TrainerService(TrainerRepository trainerRepository){
        this.trainerRepository = trainerRepository;
    }
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }

    public Optional<Trainer> getTrainerById(Long id) throws TrainerNotFound {
        Optional<Trainer> trainerWithName = trainerRepository.findById(id);
        if (trainerWithName.isPresent()){
            return trainerRepository.findById(id);
        } else {
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
    }

    public void addTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    public void deleteTrainerById(Long id) {
        trainerRepository.deleteById(id);
    }

    public void updateTrainerById(Long id, Trainer trainer) throws TrainerNotFound {
        Optional<Trainer> trainerById = trainerRepository.findById(id);
        if (trainerById.isPresent()){
            Trainer updateTrainer = trainerById.get();
            updateTrainer.setName(trainer.getName());
            updateTrainer.setAge(trainer.getAge());
            updateTrainer.setGender(trainer.getGender());
            updateTrainer.setPokemon(trainer.getPokemon());
            trainerRepository.save(updateTrainer);
        } else {
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
    }
}
