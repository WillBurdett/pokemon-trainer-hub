package com.will.pokemontrainerhub.config;

import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
public class ValidationAndHydration {
    @Bean
    public Filter validationFilter() {
        return new OpenApiValidationFilter(
                true, // enable request validation
                false  // enable response validation
        );
    }
    @Bean
    public WebMvcConfigurer addOpenApiValidationInterceptor(@Value("classpath:api-validation.json") final Resource apiSpecification) throws IOException {
        final EncodedResource specResource = new EncodedResource(apiSpecification, StandardCharsets.UTF_8);
        final OpenApiValidationInterceptor openApiValidationInterceptor = new OpenApiValidationInterceptor(specResource);
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(final InterceptorRegistry registry) {
                registry.addInterceptor(openApiValidationInterceptor);
            }
        };
    }
    @Bean
    CommandLineRunner commandLineRunner (TrainerRepository trainerRepository, PokemonRepository pokemonRepository){
        return args -> {
            Trainer sam  = new Trainer (
                    "Sam",
                    35,
                    Gender.FEMALE
            );
            Trainer bob = new Trainer (
                    "Bob",
                    21,
                    Gender.MALE
            );
            Trainer john = new Trainer (
                    "John",
                    89,
                    Gender.MALE
            );
            Pokemon bulbasaur = new Pokemon(
                    "Bulbasaur",
                    Gender.MALE,
                    7,
                    0.4,
                    5.2
            );
            Pokemon charmander = new Pokemon(
                    "Charmander",
                    Gender.MALE,
                    7,
                    0.4,
                    5.2
            );
            Pokemon squirtle = new Pokemon(
                    "Squirtle",
                    Gender.MALE,
                    7,
                    0.4,
                    5.2
            );

            pokemonRepository.saveAll(Arrays.asList(squirtle, charmander, bulbasaur));
            trainerRepository.saveAll(Arrays.asList(sam, bob, john));
        };
    }
}
