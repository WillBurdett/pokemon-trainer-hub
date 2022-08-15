package com.will.pokemontrainerhub;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class PokemonTrainerHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonTrainerHubApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (TrainerRepository trainerRepository, PokemonRepository pokemonRepository){
		return args -> {
			Trainer trainer1 = new Trainer (
					"Name",
					21,
					Gender.OTHER,
					null
			);
			Trainer trainer2 = new Trainer (
					"Name",
					21,
					Gender.OTHER,
					null
			);
			Pokemon bulbasaur = new Pokemon(
					"Bulbasaur",
					Gender.MALE,
					7,
					0.4,
					5.2,
					null
			);
			Pokemon charmander = new Pokemon(
					"Charmander",
					Gender.MALE,
					7,
					0.4,
					5.2,
					null
			);
			Pokemon squirtle = new Pokemon(
					"Squirtle",
					Gender.MALE,
					7,
					0.4,
					5.2,
					null
			);
			trainer1.setPokemon(Arrays.asList(bulbasaur));
			pokemonRepository.saveAll(Arrays.asList(squirtle, charmander));
			trainerRepository.save(trainer1);
			trainerRepository.save(trainer2);
		};
	}
}
