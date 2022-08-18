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
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        Trainer sam = new Trainer( "sam", 2, Gender.MALE);
        entityManager.persistAndFlush(bob);
        entityManager.persistAndFlush(sam);
        Trainer bobAfterDbEntry = new Trainer(1L,"bob", 2, Gender.MALE, null);
        Trainer samAfterDbEntry = new Trainer( 2L,"sam", 2, Gender.MALE, null);

        // when
        List<Trainer> actual = trainerService.getAllTrainers();
        List <Trainer> expected = Arrays.asList(bobAfterDbEntry, samAfterDbEntry);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findById_thenReturnTrainer() {
        // given
        Trainer bob = new Trainer(1L,"bob", 2, Gender.MALE, new ArrayList<>());
        entityManager.merge(bob);
        entityManager.flush();

        // when
        Optional<Trainer> actual = trainerService.getTrainerById(1L);
        ArrayList<Pokemon> actualList = ConvertCollections.pokemonToArrayList(actual.get().getPokemon());
        ArrayList<Pokemon> expectedList = ConvertCollections.pokemonToArrayList(bob.getPokemon());

        // then
        assertThat(actual.get().getId()).isEqualTo(bob.getId());
        assertThat(actual.get().getName()).isEqualTo(bob.getName());
        assertThat(actual.get().getAge()).isEqualTo(bob.getAge());
        assertThat(actual.get().getGender()).isEqualTo(bob.getGender());
        assertThat(actualList).isEqualTo(expectedList);

    }

    // TODO: 18/08/2022 carry on tests from here
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
