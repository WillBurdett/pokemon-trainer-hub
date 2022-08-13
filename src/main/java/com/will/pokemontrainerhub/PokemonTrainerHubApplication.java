package com.will.pokemontrainerhub;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PokemonTrainerHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonTrainerHubApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (TrainerRepository trainerRepository){
		return args -> {
			Trainer trainer = new Trainer (
					"Name",
					21,
					Gender.OTHER,
					null
			);
			trainerRepository.save(trainer);
		};
	}
}
