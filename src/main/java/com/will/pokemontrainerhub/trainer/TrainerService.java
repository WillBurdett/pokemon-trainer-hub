package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import com.will.pokemontrainerhub.Exceptions.TrainerNotFound;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    // TODO: 15/08/2022 Check if this is best practise (multiple instantiations for one to many)
    private final PokemonRepository pokemonRepository;

    @Autowired
    TrainerService(TrainerRepository trainerRepository, PokemonRepository pokemonRepository){
        this.trainerRepository = trainerRepository;
        this.pokemonRepository = pokemonRepository;
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

    public void addPokemonToTrainer(Long id, Long pokemonId) throws TrainerNotFound, PokemonNotFound {
        Optional<Trainer> trainer = trainerRepository.findById(id);
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);

        if (trainer.isPresent() && pokemon.isPresent()){
            trainer.get().getPokemon().add(pokemon.get());
            trainerRepository.save(trainer.get());
        } else if (!trainer.isPresent()){
            throw new TrainerNotFound("trainer with id " + id + " not found");
        } else if (!pokemon.isPresent()){
            throw new PokemonNotFound("pokemon with id " + pokemonId + " not found");
        }
    }
}
