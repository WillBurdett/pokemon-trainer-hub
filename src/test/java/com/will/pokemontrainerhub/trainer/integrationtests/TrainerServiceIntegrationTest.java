package com.will.pokemontrainerhub.trainer.integrationtests;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.pokemon.Pokemon;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import com.will.pokemontrainerhub.trainer.TrainerService;
import com.will.pokemontrainerhub.utils.ConvertCollections;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class TrainerServiceIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TrainerService trainerService;

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
        entityManager.persistAndFlush(bob);

        // when
        List <Trainer> actual = trainerService.getAllTrainers();

        ArrayList<Pokemon> actualList1 = ConvertCollections.pokemonToArrayList(actual.get(0).getPokemon());
        ArrayList<Pokemon> expectedList1 = ConvertCollections.pokemonToArrayList(bob.getPokemon());

        // then
        assertThat(actual.get(0).getId()).isEqualTo(1L);
        assertThat(actual.get(0).getName()).isEqualTo(bob.getName());
        assertThat(actual.get(0).getAge()).isEqualTo(bob.getAge());
        assertThat(actual.get(0).getGender()).isEqualTo(bob.getGender());
        assertThat(actualList1).isEqualTo(expectedList1);
    }

    @Test
    public void findById_thenReturnTrainer() {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);
        entityManager.merge(bob);
        entityManager.flush();

        // when
        Optional<Trainer> actual = trainerService.getTrainerById(1L);
        ArrayList<Pokemon> actualList = ConvertCollections.pokemonToArrayList(actual.get().getPokemon());
        ArrayList<Pokemon> expectedList = ConvertCollections.pokemonToArrayList(bob.getPokemon());

        // then
        assertThat(actual.get().getId()).isEqualTo(1L);
        assertThat(actual.get().getName()).isEqualTo(bob.getName());
        assertThat(actual.get().getAge()).isEqualTo(bob.getAge());
        assertThat(actual.get().getGender()).isEqualTo(bob.getGender());
        assertThat(actualList).isEqualTo(expectedList);

    }

    @Test
    public void savesTrainer_UpdatesTrainer() {
        // given
        Trainer bob = new Trainer("bob", 2, Gender.MALE);

        // when
        trainerRepository.save(bob);

        // then
        assertThat(trainerRepository.findById(1L)).isEqualTo(Optional.of(bob));
    }

    @Test
    public void deleteById_DeletesTrainer() {
        // given
        Trainer bob = new Trainer ("bob", 2, Gender.MALE);
        entityManager.persist(bob);
        entityManager.flush();

        System.out.println(trainerRepository.findAll());

        // when
        trainerRepository.deleteById(1L);

        // then
        assertThat(trainerRepository.findAll().size()).isEqualTo(0);
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
