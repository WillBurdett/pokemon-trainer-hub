package com.will.pokemontrainerhub.trainer.integrationtests;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.PokemonRepository;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TrainerRepoIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TrainerRepository trainerRepository;

    @After
    public void tearDown(){
        trainerRepository.deleteAll();
    }

    @Test
    public void findAll_ReturnsAllTrainers() throws Exception {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        Trainer sam = new Trainer("sam", 2, Gender.MALE);
        entityManager.persistAndFlush(bob);
        entityManager.persistAndFlush(sam);

        // when
        List<Trainer> actual = trainerRepository.findAll();
        List <Trainer> expected = Arrays.asList(bob, sam);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findByName_thenReturnTrainer() {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        entityManager.persistAndFlush(bob);

        // when
        Optional<Trainer> actual = trainerRepository.findById(1L);

        // then
        assertThat(actual.get()).isEqualTo(bob);
    }

    @Test
    public void deleteById_DeletesTrainer() {
        // given
        Trainer bob = new Trainer ("bob", 2, Gender.MALE);
        entityManager.persist(bob);
        entityManager.flush();

        // when
        trainerRepository.deleteById(1L);

        // then
        assertThat(trainerRepository.findById(1L)).isEqualTo(Optional.empty());
    }

    @Test
    public void UpdateById_UpdatesTrainer() {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        entityManager.persistAndFlush(bob);

        // when
        bob.setGender(Gender.FEMALE);
        trainerRepository.save(bob);

        // then
        assertThat(trainerRepository.findById(1L).get().getGender()).isEqualTo(Gender.FEMALE);
    }





}
