package com.will.pokemontrainerhub.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
