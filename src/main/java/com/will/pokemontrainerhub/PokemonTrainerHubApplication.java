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
			Trainer trainer = new Trainer (
					"Name",
					21,
					Gender.OTHER,
					null
			);
			Pokemon pokemon = new Pokemon(
					"Bulbasaur",
					Gender.MALE,
					7,
					0.4,
					5.2,
					null
			);
			trainer.setPokemon(Arrays.asList(pokemon));
			trainerRepository.save(trainer);
		};
	}
}
