package com.will.pokemontrainerhub.trainer.integrationtests;

import com.will.pokemontrainerhub.Enums.Gender;
import com.will.pokemontrainerhub.trainer.Trainer;
import com.will.pokemontrainerhub.trainer.TrainerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
    public void findById_thenReturnTrainer() {
        // given
        Trainer bob = new Trainer(1L, "bob", 2, Gender.MALE, new ArrayList<>());
        entityManager.merge(bob);
        entityManager.flush();

        System.out.println(trainerRepository.findAll());
        // when
        Optional<Trainer> actual = trainerRepository.findById(1L);

        // then
        assertThat(actual.get().getId()).isEqualTo(bob.getId());
        assertThat(actual.get().getName()).isEqualTo(bob.getName());
        assertThat(actual.get().getAge()).isEqualTo(bob.getAge());
        assertThat(actual.get().getGender()).isEqualTo(bob.getGender());
        // TODO: 18/08/2022 implement a way to comprehensively compare/test lists
        // assertThat(actual.get().getPokemon()).isEqualTo(bob.getPokemon());
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
