package com.will.pokemontrainerhub.trainer.unittests;

import com.google.common.collect.Lists;
import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import com.will.pokemontrainerhub.trainer.TrainerReqBody;
import com.will.pokemontrainerhub.trainer.TrainerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainerService.class)
class TrainerServiceTest {

    @Autowired
    TrainerService trainerService;

    @MockBean
    TrainerRepository trainerRepository;

    @MockBean
    PokemonRepository pokemonRepository;

    @Test
    void getAllTrainers() {
        // given
        Trainer bob = new Trainer("bob", 1, Gender.MALE);
        List<Trainer> expected = Arrays.asList(bob);
        when(trainerRepository.findAll()).thenReturn(Arrays.asList(bob));

        // when
        List<Trainer> actual = trainerService.getAllTrainers();

        // then
        verify(trainerRepository, times(1)).findAll();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getTrainerById() {
        // given
        Trainer bob = new Trainer("bob", 1, Gender.MALE);;
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(bob));

        // when
        Optional<Trainer> actual = trainerService.getTrainerById(1L);

        // then
        verify(trainerRepository, times(1)).findById(1L);
        assertThat(actual).isEqualTo(Optional.of(bob));
    }

    @Test
    void addTrainer() {
        // given
        TrainerReqBody bobReqBody = new TrainerReqBody("bob", 1, Gender.MALE);
        Trainer bob = new Trainer (bobReqBody.getName(), bobReqBody.getAge(), bobReqBody.getGender());
        // when
        trainerService.addTrainer(bobReqBody);
        // then
        verify(trainerRepository, times( 1)).save(bob);
    }

    @Test
    void deleteTrainerById() {
        // given
        Long id = 1L;
        // when
        trainerService.deleteTrainerById(id);
        // then
        verify(trainerRepository, times( 1)).deleteById(id);
    }

    @Test
    void updateTrainerById() {
        // given
        TrainerReqBody bobReqBody = new TrainerReqBody("bob", 1, Gender.MALE);
        Trainer bob = new Trainer (bobReqBody.getName(), bobReqBody.getAge(), bobReqBody.getGender());
        // when
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(bob));
        trainerService.updateTrainerById(1L, bobReqBody);
        // then
        verify(trainerRepository, times( 1)).save(bob);
    }

    @Test
    void addOnePokemonByIdToTrainer() {
        // given
        Trainer bob = new Trainer(1L, "bob", 1, Gender.MALE, new ArrayList<>());
        Pokemon pikachu = new Pokemon(1L, "pikachu", Gender.FEMALE, 3, 3.3, 3.3, null);
        // when
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pikachu));
        trainerService.addOnePokemonByIdToTrainer(bob, 1L);
        // then
        Trainer expected = new Trainer(1L, "bob", 1, Gender.MALE, Arrays.asList(pikachu));
        verify(trainerRepository, times( 1)).save(expected);
    }

    @Test
    void removeOnePokemonByIdFromTrainer() {
        // given
        Pokemon pikachu = new Pokemon(1L, "pikachu", Gender.FEMALE, 3, 3.3, 3.3, null);
        Trainer bob = new Trainer(1L, "bob", 1, Gender.MALE, new ArrayList<>());
        bob.getPokemon().add(pikachu);
        assertThat(bob.getPokemon().size()).isEqualTo(1);
        // when
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pikachu));
        trainerService.removeOnePokemonByIdFromTrainer(bob, 1L);
        // then
        assertThat(bob.getPokemon().size()).isEqualTo(0);
        verify(trainerRepository, times( 1)).save(bob);
    }

    @Test
    void addPokemonToTrainer() {
        // given
        Trainer bob = new Trainer(1L, "bob", 1, Gender.MALE, new ArrayList<>());
        Pokemon pikachu = new Pokemon(1L, "pikachu", Gender.FEMALE, 3, 3.3, 3.3, null);
        // when
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(bob));
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pikachu));
        trainerService.addPokemonToTrainer(1L, "1");
        // then
        bob.getPokemon().add(pikachu);
        verify(trainerRepository, times( 1)).save(bob);
    }

    @Test
    void removePokemonFromTrainer() {
        // given
        Pokemon pikachu = new Pokemon(1L, "pikachu", Gender.FEMALE, 3, 3.3, 3.3, null);
        Trainer bob = new Trainer(1L, "bob", 1, Gender.MALE, new ArrayList<>());
        bob.getPokemon().add(pikachu);
        assertThat(bob.getPokemon().size()).isEqualTo(1);
        // when
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(bob));
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pikachu));
        trainerService.removePokemonFromTrainer(1L, "1");
        // then
        assertThat(bob.getPokemon().size()).isEqualTo(0);
        verify(trainerRepository, times( 1)).save(bob);
    }

    @Test
    void removeAllPokemonFromTrainer() {
        // given
        Pokemon pikachu = new Pokemon(1L, "pikachu", Gender.FEMALE, 3, 3.3, 3.3, null);
        Pokemon bulbasaur = new Pokemon(1L, "bulbasaur", Gender.FEMALE, 3, 3.3, 3.3, null);
        Pokemon charmander = new Pokemon(1L, "charmander", Gender.FEMALE, 3, 3.3, 3.3, null);

        Trainer bob = new Trainer(1L, "bob", 1, Gender.MALE, new ArrayList<>());
        bob.getPokemon().add(pikachu);
        bob.getPokemon().add(bulbasaur);
        bob.getPokemon().add(charmander);

        assertThat(bob.getPokemon().size()).isEqualTo(3);
        // when
        when(trainerRepository.findById(1L)).thenReturn(Optional.of(bob));
        trainerService.removeAllPokemonFromTrainer(1L);
        // then
        assertThat(bob.getPokemon().size()).isEqualTo(0);
        verify(trainerRepository, times( 1)).save(bob);
    }
}