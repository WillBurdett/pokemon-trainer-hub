package com.will.pokemontrainerhub.trainer;

import com.will.pokemontrainerhub.Exceptions.NoTrainersFound;
import com.will.pokemontrainerhub.Exceptions.PokemonNotFound;
import com.will.pokemontrainerhub.Exceptions.TrainerNotFound;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List <Trainer> allTrainers = trainerRepository.findAll();
        if (allTrainers.size() == 0){
            throw new NoTrainersFound("no trainers found");
        }
        return allTrainers;
    }

    public Optional<Trainer> getTrainerById(Long id) throws TrainerNotFound {
        Optional<Trainer> trainerWithName = trainerRepository.findById(id);
        if (trainerWithName.isPresent()){
            return trainerWithName;
        } else {
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
    }

    public void addTrainer(TrainerReqBody trainer) {
        trainerRepository.save(new Trainer(trainer.getName(), trainer.getAge(), trainer.getGender()));
    }

    public void deleteTrainerById(Long id) {
        Optional<Trainer> trainerWithName = trainerRepository.findById(id);
        if (trainerWithName.isPresent()){
            trainerRepository.deleteById(id);
        } else {
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
    }

    public void updateTrainerById(Long id, TrainerReqBody trainer) throws TrainerNotFound {
        Optional<Trainer> trainerById = trainerRepository.findById(id);
        if (trainerById.isPresent()){
            Trainer updateTrainer = trainerById.get();
            updateTrainer.setName(trainer.getName());
            updateTrainer.setAge(trainer.getAge());
            updateTrainer.setGender(trainer.getGender());
            trainerRepository.save(updateTrainer);
        } else {
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
    }

    public void addOnePokemonByIdToTrainer(Trainer trainer, Long pokemonId) throws TrainerNotFound, PokemonNotFound {
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if (pokemon.isPresent()){
            trainer.getPokemon().add(pokemon.get());
            trainerRepository.save(trainer);
        }  else if (!pokemon.isPresent()){
            throw new PokemonNotFound("pokemon with id " + pokemonId + " not found");
        }
    }

    public void removeOnePokemonByIdFromTrainer(Trainer trainer, Long pokemonId) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if (pokemon.isPresent()){
            List<Pokemon> currentPokemon = trainer.getPokemon();
            for (int i = 0; i < currentPokemon.size(); i++){
                if (currentPokemon.get(i).getId() == pokemonId){
                    currentPokemon.remove(i);
                    trainer.setPokemon(currentPokemon);
                    trainerRepository.save(trainer);
                    break;
                }
            }
        } else if (!pokemon.isPresent()){
            throw new PokemonNotFound("pokemon with id " + pokemonId + " not found");
        }
    }

    public void addPokemonToTrainer(Long id, String allPokemonIds) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if (!trainerOptional.isPresent()){
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
        try {
            String[] pokemonIdList = allPokemonIds.split(",");
            for (String s: pokemonIdList){
                addOnePokemonByIdToTrainer(trainerOptional.get(), Long.parseLong(s));
            }
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void removePokemonFromTrainer(Long id, String allPokemonIds) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if (!trainerOptional.isPresent()){
            throw new TrainerNotFound("trainer with id " + id + " not found");
        }
        try {
            String[] pokemonIdList = allPokemonIds.split(",");
            for (String s: pokemonIdList){
                removeOnePokemonByIdFromTrainer(trainerOptional.get(), Long.parseLong(s));
            }
        } catch (Exception e){
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void removeAllPokemonFromTrainer(Long id) {
        Optional<Trainer> trainerOptional = trainerRepository.findById(id);
        if (!trainerOptional.isPresent()){
            throw new TrainerNotFound("trainer with id " + id + " not found");
        } else {
            trainerOptional.get().setPokemon(new ArrayList<>());
            trainerRepository.save(trainerOptional.get());
        }
    }
}
